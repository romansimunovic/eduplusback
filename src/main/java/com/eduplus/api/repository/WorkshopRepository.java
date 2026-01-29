package com.eduplus.api.repository;

import com.eduplus.api.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    List<Workshop> findByActiveTrue();
    List<Workshop> findByTitleContainingIgnoreCase(String title);
}   