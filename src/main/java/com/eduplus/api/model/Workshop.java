package com.eduplus.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "workshops")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private LocalDate date;
    private String location;
    private Integer maxCapacity;

    @Builder.Default
    private boolean active = true;
}