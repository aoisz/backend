package com.example.backend.repository;

import com.example.backend.model.Certificates;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificates,Integer> {
//    public List<Certificates> findAll();
}
