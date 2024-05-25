package com.example.backend.controller;

import com.example.backend.model.Certificates;
import com.example.backend.service.CertificateService;
import com.example.backend.template.TempCertificate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private CertificateService service;
    @GetMapping("/getAll")
    public List<Certificates> getAllCertificate() {
        return service.getAllCertificate();
    }
    @PostMapping("/get/{id}")
    public Certificates getById(@PathVariable("id") int id) {
        return service.getById(id);
    }
    @PostMapping("/create")
    public boolean create(@RequestBody TempCertificate tempCertificate) {
        service.create(tempCertificate);
        return true;
    }
}
