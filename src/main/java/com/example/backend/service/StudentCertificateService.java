package com.example.backend.service;

import com.example.backend.model.StudentCertificate;
import java.util.List;

public interface StudentCertificateService {
    public List<StudentCertificate> getAllUserCertificate();
    public List<StudentCertificate> getByCeritificateId(int id);
}
