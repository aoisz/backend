package com.example.backend.service;

import com.example.backend.model.Certificates;
import com.example.backend.template.TempCertificate;
import java.util.List;

public interface CertificateService {
    public List<Certificates> getAllCertificate();
    public Certificates getById(int id);
    public boolean create(TempCertificate tempCertificate);
}
