/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.controller;

import com.example.backend.service.OcrService;
import com.example.backend.template.CertificateInformation;
import com.example.backend.template.CertificateScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/ocr")
public class OcrController {
    @Autowired
    private OcrService ocrService;
    
    @PostMapping("/upload/information")
    public ResponseEntity<CertificateInformation> processInformation(@RequestParam("file") MultipartFile file, @RequestParam("studentId") String studentId) {
        return ResponseEntity.ok(ocrService.getInformation(file, studentId));
    }
    
    @PostMapping("/upload/score")
    public ResponseEntity<CertificateScore> processScore(@RequestParam("file") MultipartFile file, @RequestParam("studentId") String studentId) {
        return ResponseEntity.ok(ocrService.getScore(file, studentId));
    }
}
