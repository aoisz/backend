package com.example.backend.service;

import com.example.backend.model.Certificate;
import com.example.backend.repository.CertificateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService{
    @Autowired
    private CertificateRepository repository;

    @Override
    public List<Certificate> getAllCertificate() {
        return repository.findAll();
    }

    @Override
    public Certificate getById(int id) {
        return null;
    }
    
}
