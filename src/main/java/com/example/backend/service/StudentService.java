package com.example.backend.service;

import java.util.List;
import com.example.backend.model.Students;
import org.springframework.data.jpa.repository.Query;

public interface StudentService {
    public List<Students> getAllStudent();
    public Students getById(int id);
    public Students getByStudentId(String id);
}
