package com.eduplus.api.repository;

import com.eduplus.api.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> findByLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String lastName, String email);
}