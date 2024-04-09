package com.example.backend.service;

import com.example.backend.model.Certificates;
import java.util.List;

public interface CertificateService {
    public List<Certificates> getAllCertificate();
    public Certificates getById(int id);
}
