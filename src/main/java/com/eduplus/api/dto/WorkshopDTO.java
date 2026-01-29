package com.eduplus.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WorkshopDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private Integer maxCapacity;
    private boolean active;
}