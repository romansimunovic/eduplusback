package com.eduplus.api.controller;

import com.eduplus.api.model.Attendee;
import com.eduplus.api.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<List<Attendee>> getAll() {
        return ResponseEntity.ok(attendeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(attendeeService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Attendee>> search(@RequestParam String q) {
        return ResponseEntity.ok(attendeeService.search(q));
    }

    @PostMapping
    public ResponseEntity<Attendee> create(@RequestBody Attendee attendee) {
        return new ResponseEntity<>(attendeeService.save(attendee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> update(@PathVariable Long id, @RequestBody Attendee attendee) {
        attendee.setId(id);
        return ResponseEntity.ok(attendeeService.save(attendee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}