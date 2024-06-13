package com.example.backend.service;

import com.example.backend.model.Students;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository; 
    
    @Override
    public List<Students> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public Students getById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public Students getByStudentId(String studentId) {
        return repository.findByStudentId(studentId).get();
    }
}
