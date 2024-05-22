package com.example.backend.controller;

import com.example.backend.model.StudentCertificate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.service.StudentCertificateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/student_certificate")
public class StudentCertificateController {
    @Autowired
    private StudentCertificateService service;
    @GetMapping("/getAll")
    public List<StudentCertificate> getAllUserCertificate() {
        return service.getAllUserCertificate();
    }
    
    @GetMapping("/getByCertificateId/{certificate_id}")
    public List<StudentCertificate> getByCertificateId(@PathVariable("certificate_id") int certificate_id) {
        return service.getByCeritificateId(certificate_id);
    }
    
    @GetMapping("/getCertificateByStudentId/{student_id}")
    public List<StudentCertificate> getCertificateByStudentId(@PathVariable("student_id") String student_id) {
        return service.getCertificateByStudentId(student_id);
    }
    

    @GetMapping("/delete/{certificate_id}")
    public boolean deleteUserCertificate(@PathVariable("certificate_id") Integer certificateId) {
        service.deleteById(certificateId);
        return true;
    }
}
