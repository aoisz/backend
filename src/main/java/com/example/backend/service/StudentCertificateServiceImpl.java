package com.example.backend.service;

import com.example.backend.model.Certificates;
import com.example.backend.model.StudentCertificate;
import com.example.backend.model.Students;
import com.example.backend.repository.CertificateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.StudentCertificateRepository;
import static java.lang.Integer.parseInt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class StudentCertificateServiceImpl implements StudentCertificateService {

    @Autowired
    private StudentCertificateRepository repository;
    @Autowired
    private CertificateService certService;
    @Autowired
    private StudentService studentService;
    
    @Override
    public List<StudentCertificate> getAllUserCertificate() {
       return repository.findAll();
    }
    
    @Override
    public StudentCertificate getById(int id) {
        return repository.findById(id).get();
    }
    
    @Override
    public List<StudentCertificate> getByCeritificateId(int id) {
        Certificates cert = certService.getById(id);
        return repository.findByCertificate(cert);
    }
 
    @Override
    public List<StudentCertificate> getCertificateByStudentId(String student_id) {
        Students student = studentService.getByStudentId(student_id);
        return repository.findByStudent(student);
    }
    
    @Override
    public boolean deleteById(Integer certificateId) {
        try {
            repository.deleteById(certificateId);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("Không tìm chứng chỉ để xóa");
            return false;
        } catch (DataIntegrityViolationException ex) {
            System.out.println("Lỗi liên quan đến tính toàn vẹn dữ liệu hoặc ràng buộc khóa ngoại");
            return false;
        }
    }

}
