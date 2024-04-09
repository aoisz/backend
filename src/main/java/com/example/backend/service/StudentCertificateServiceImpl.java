package com.example.backend.service;

import com.example.backend.model.Certificates;
import com.example.backend.model.StudentCertificate;
import com.example.backend.repository.CertificateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.StudentCertificateRepository;

@Service
public class StudentCertificateServiceImpl implements StudentCertificateService {

    @Autowired
    private StudentCertificateRepository repository;
    @Autowired
    private CertificateService certService;
    
    @Override
    public List<StudentCertificate> getAllUserCertificate() {
       return repository.findAll();
    }
    
    @Override
    public List<StudentCertificate> getByCeritificateId(int id) {
        Certificates cert = certService.getById(id);
        return repository.findByCertificate(cert);
    }
}
