package com.eduplus.api.dto;

import com.eduplus.api.model.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AttendanceResponse {
    private Long id;
    private String attendeeName;
    private String workshopTitle;
    private AttendanceStatus status;
}