package com.eduplus.api.repository;

import com.eduplus.api.model.Attendance;
import com.eduplus.api.model.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByWorkshopId(Long workshopId);
    List<Attendance> findByAttendeeId(Long attendeeId);
    boolean existsByAttendeeIdAndWorkshopId(Long attendeeId, Long workshopId);
}