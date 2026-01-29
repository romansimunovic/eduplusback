package com.eduplus.api.dto;

import com.eduplus.api.model.AttendanceStatus;
import lombok.Data;

@Data
public class AttendanceRequest {
    private Long attendeeId;
    private Long workshopId;
    private AttendanceStatus status; // Usually starts as PENDING
}