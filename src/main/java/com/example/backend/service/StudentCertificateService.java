package com.example.backend.service;

import com.example.backend.model.StudentCertificate;
import java.util.List;

public interface StudentCertificateService {
    public StudentCertificate getById(int id);
    public List<StudentCertificate> getAllUserCertificate();
    public List<StudentCertificate> getByCeritificateId(int id);
    public List<StudentCertificate> getCertificateByStudentId(String id);
    public boolean deleteById(Integer certificateId);
}
