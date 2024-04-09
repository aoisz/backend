package com.example.backend.service;

import java.util.List;
import com.example.backend.model.Students;

public interface StudentService {
    public List<Students> getAllStudent();
    public Students getById(int id);
}
