package com.example.backend.service;

import com.example.backend.template.CertificateInformation;
import com.example.backend.template.CertificateScore;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {
//    public OcrResult extract(MultipartFile file);
    public CertificateInformation getInformation(MultipartFile file, String studentId);
    public CertificateScore getScore(MultipartFile file, String studentId);
}
