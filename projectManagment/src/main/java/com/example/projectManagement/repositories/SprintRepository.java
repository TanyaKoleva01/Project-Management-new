package com.example.projectManagement.repositories;

import com.example.projectManagement.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Sprint getSprintByName(String name);
}
