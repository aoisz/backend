/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.CertificateStatus;

/**
 *
 * @author phmlhuyntrang
 */
public interface CertificateStatusService {
    public CertificateStatus getById(int id);
    public CertificateStatus getByName(String name);
}
