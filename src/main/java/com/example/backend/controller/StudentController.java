package com.example.backend.controller;

import com.example.backend.model.Students;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;
    @GetMapping("/getAll")
    public List<Students> getAllStudent() {
        return service.getAllStudent();
    }
    @GetMapping("/getByStudentId/{student_id}")
    public ResponseEntity<Students> getByStudentId(@PathVariable("student_id") String student_id) {
        return ResponseEntity.ok(service.getByStudentId(student_id));
    }
    
}
