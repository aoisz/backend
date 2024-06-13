/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.controller;

import com.example.backend.model.CertificateImage;
import com.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class UploadImageController {
    @Autowired
    private ImageService service;
    @PostMapping("/upload")
    public ResponseEntity uploadImage(
            @RequestParam(name = "filePath") String filePath, 
            @RequestParam(name = "type") String type,
            @RequestParam(name = "studentId") String studentId
    ) {
        String fileName = service.uploadImage(filePath, type, studentId);
        return ResponseEntity.ok(fileName);
    }
}
