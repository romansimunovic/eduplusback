package com.eduplus.api.service;

import com.eduplus.api.model.Attendee;
import com.eduplus.api.repository.AttendeeRepository;
import com.eduplus.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Transactional(readOnly = true)
    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Attendee findById(Long id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Attendee> search(String query) {
        return attendeeRepository.findByLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    @Transactional
    public Attendee save(Attendee attendee) {
        // Professional check: don't allow duplicate emails
        if (attendee.getId() == null && attendeeRepository.findByLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase("", attendee.getEmail()).stream().anyMatch(a -> a.getEmail().equals(attendee.getEmail()))) {
            throw new RuntimeException("An attendee with this email already exists.");
        }
        return attendeeRepository.save(attendee);
    }

    @Transactional
    public void delete(Long id) {
        if (!attendeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Attendee not found.");
        }
        attendeeRepository.deleteById(id);
    }
}