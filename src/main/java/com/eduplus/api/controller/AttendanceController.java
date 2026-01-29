package com.eduplus.api.controller;

import com.eduplus.api.dto.AttendanceRequest;
import com.eduplus.api.dto.AttendanceResponse;
import com.eduplus.api.model.AttendanceStatus;
import com.eduplus.api.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<AttendanceResponse> register(@RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.registerAttendee(request));
    }

    @GetMapping("/workshop/{workshopId}")
    public ResponseEntity<List<AttendanceResponse>> getByWorkshop(@PathVariable Long workshopId) {
        return ResponseEntity.ok(attendanceService.getByWorkshop(workshopId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AttendanceResponse> updateStatus(
            @PathVariable Long id, 
            @RequestParam AttendanceStatus status) {
        return ResponseEntity.ok(attendanceService.updateStatus(id, status));
    }
}