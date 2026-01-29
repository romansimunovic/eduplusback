package com.eduplus.api.controller;

import com.eduplus.api.model.Workshop;
import com.eduplus.api.service.WorkshopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
@RequiredArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;

    @GetMapping
    public ResponseEntity<List<Workshop>> getAll() {
        return ResponseEntity.ok(workshopService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Workshop>> getActive() {
        return ResponseEntity.ok(workshopService.findActive());
    }

    @PostMapping
    public ResponseEntity<Workshop> create(@RequestBody Workshop workshop) {
        return new ResponseEntity<>(workshopService.save(workshop), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workshopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}