package com.eduplus.api.service;

import com.eduplus.api.dto.AttendanceRequest;
import com.eduplus.api.dto.AttendanceResponse;
import com.eduplus.api.model.Attendance;
import com.eduplus.api.model.AttendanceStatus;
import com.eduplus.api.repository.AttendanceRepository;
import com.eduplus.api.repository.AttendeeRepository;
import com.eduplus.api.repository.WorkshopRepository;
import com.eduplus.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendeeRepository attendeeRepository;
    private final WorkshopRepository workshopRepository;

    @Transactional
    public AttendanceResponse registerAttendee(AttendanceRequest request) {
        // 1. Check if both exist
        var attendee = attendeeRepository.findById(request.getAttendeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found"));
        var workshop = workshopRepository.findById(request.getWorkshopId())
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found"));

        // 2. Check if already registered
        if (attendanceRepository.existsByAttendeeIdAndWorkshopId(request.getAttendeeId(), request.getWorkshopId())) {
            throw new RuntimeException("Attendee is already registered for this workshop.");
        }

        // 3. Save
        Attendance attendance = Attendance.builder()
                .attendee(attendee)
                .workshop(workshop)
                .status(request.getStatus() != null ? request.getStatus() : AttendanceStatus.PENDING)
                .build();

        var saved = attendanceRepository.save(attendance);
        return mapToResponse(saved);
    }

    public List<AttendanceResponse> getByWorkshop(Long workshopId) {
        return attendanceRepository.findByWorkshopId(workshopId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional
    public AttendanceResponse updateStatus(Long id, AttendanceStatus status) {
        var attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found"));
        attendance.setStatus(status);
        return mapToResponse(attendanceRepository.save(attendance));
    }

    private AttendanceResponse mapToResponse(Attendance a) {
        return AttendanceResponse.builder()
                .id(a.getId())
                .attendeeName(a.getAttendee().getFirstName() + " " + a.getAttendee().getLastName())
                .workshopTitle(a.getWorkshop().getTitle())
                .status(a.getStatus())
                .build();
    }
}