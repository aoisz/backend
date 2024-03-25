package com.example.backend.controller;

import com.example.backend.model.Certificate;
import com.example.backend.service.CertificateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private CertificateService service;
    @GetMapping("/getAll")
    public List<Certificate> getAllCertificate() {
        return service.getAllCertificate();
    }
}
