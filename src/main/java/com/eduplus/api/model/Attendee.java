package com.eduplus.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer birthYear;
    private String phoneNumber;
    private String city;
    private String notes;
}