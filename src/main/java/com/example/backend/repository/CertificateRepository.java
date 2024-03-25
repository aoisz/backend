package com.example.backend.repository;

import com.example.backend.model.Certificate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
    public List<Certificate> findAll();
}
