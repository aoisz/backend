/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.CertificateStatus;
import com.example.backend.repository.CertificateStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phmlhuyntrang
 */
@Service
public class CertificateStatusServiceImpl implements CertificateStatusService {
    @Autowired
    private CertificateStatusRepository repository;
    
    @Override
    public CertificateStatus getById(int id) {
        return repository.findById(id).get();
    }
    
    @Override
    public CertificateStatus getByName(String name) {
        return repository.findByName(name).get(0);
    }
}
