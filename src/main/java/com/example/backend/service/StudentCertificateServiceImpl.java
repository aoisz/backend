package com.example.backend.service;

import com.example.backend.model.CertificateImage;
import com.example.backend.model.Certificates;
import com.example.backend.model.StudentCertificate;
import com.example.backend.model.Students;
import com.example.backend.repository.CertificateRepository;
import com.example.backend.repository.ImageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.StudentCertificateRepository;
import com.example.backend.template.TempCertificate;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class StudentCertificateServiceImpl implements StudentCertificateService {

    @Autowired
    private StudentCertificateRepository repository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CertificateService certService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ImageService imageService;
    
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
    public boolean create(TempCertificate tempCertificate, String studentId) {
        StudentCertificate studentCert = new StudentCertificate();
        CertificateImage img = new CertificateImage();
        List<StudentCertificate> list = getCertificateByStudentId(studentId);
        for(StudentCertificate stdnCert : list) {
            if(stdnCert.getCertificate().getId() == 1 && stdnCert.getStudent().getStudentId().equals(studentId)) {
                studentCert = stdnCert;
                img = imageRepository.findById(stdnCert.getImages().getId()).get();
                break;
            }
        }
        HashMap<String, String> srcImage = new HashMap<>();
        srcImage.put("full", tempCertificate.full.imageURL);
        srcImage.put("information", tempCertificate.information.imageURL);
        srcImage.put("score", tempCertificate.score.imageURL);
        srcImage = imageService.uploadImages(srcImage, studentId);
        img.setFullImage(srcImage.get("full"));
        img.setInforImage(srcImage.get("information"));
        img.setScoreImage(srcImage.get("score"));
        imageRepository.save(img);
        studentCert.setImages(img);
        studentCert.setCertificate(certService.getById(1));
        studentCert.setStudent(studentService.getByStudentId(studentId));
        studentCert.setStartDate(formatDate(tempCertificate.information.testDate));
        studentCert.setExpiredDate(tempCertificate.information.validUntil);
        studentCert.setListeningScore(tempCertificate.score.listeningScore);
        studentCert.setReadingScore(tempCertificate.score.readingScore);
        studentCert.setTotalScore(tempCertificate.score.totalScore);
        repository.save(studentCert);
        return true;
    }
    
    public static String formatDate(String text) {
        String[] temp = text.split("/");
        List<String> list = Arrays.asList(temp);
        Collections.reverse(list);
        return list.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("/", "", ""));
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
