package com.eduplus.api.service;

import com.eduplus.api.model.Workshop;
import com.eduplus.api.repository.WorkshopRepository;
import com.eduplus.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    public List<Workshop> findAll() {
        return workshopRepository.findAll();
    }

    public List<Workshop> findActive() {
        return workshopRepository.findByActiveTrue();
    }

    public Workshop findById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workshop not found with ID: " + id));
    }

    @Transactional
    public Workshop save(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Transactional
    public void delete(Long id) {
        if (!workshopRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Workshop not found.");
        }
        workshopRepository.deleteById(id);
    }
}