/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.backend.repository;

import com.example.backend.model.CertificateStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phmlhuyntrang
 */
public interface CertificateStatusRepository extends JpaRepository<CertificateStatus, Integer>{
    public List<CertificateStatus> findByName(String name);
}
