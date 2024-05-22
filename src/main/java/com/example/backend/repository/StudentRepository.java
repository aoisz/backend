package com.example.backend.repository;

import com.example.backend.model.Students;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer> {
    public Optional<Students> findByStudentId(String studentId);
}
