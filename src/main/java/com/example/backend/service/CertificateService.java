package com.example.backend.service;

import com.example.backend.model.Certificate;
import java.util.List;

public interface CertificateService {
    public List<Certificate> getAllCertificate();
    public Certificate getById(int id);
}
