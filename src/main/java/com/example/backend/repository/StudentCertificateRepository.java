package com.example.backend.repository;

import com.example.backend.model.Certificates;
import com.example.backend.model.StudentCertificate;
import com.example.backend.model.Students;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCertificateRepository extends JpaRepository<StudentCertificate, Integer> {
    public List<StudentCertificate> findByStudent(Students student);
    public List<StudentCertificate> findByCertificate(Certificates certificate);
    
//    public boolean deleteById(Integer certificateId);
//    public List<User_Certificate> findAll();
}
