package com.example.backend.service;

import com.example.backend.model.Certificates;
import com.example.backend.model.StudentCertificate;
import com.example.backend.repository.CertificateRepository;
import com.example.backend.template.TempCertificate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService{
    @Autowired
    private CertificateRepository repository;

    @Override
    public List<Certificates> getAllCertificate() {
        System.out.print(repository.findAll());
        return repository.findAll();
    }

    @Override
    public Certificates getById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean create(TempCertificate tempCertificate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
