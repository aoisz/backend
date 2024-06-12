/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.CertificateImage;
import com.example.backend.repository.ImageRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobWriteOption;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository repository;
    private Storage storage;
    
    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
            storage = StorageOptions.newBuilder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                    setProjectId("backend-c4e58").build().getService();
        } catch (IOException ex) {
        }
    }
    
    @Override
    public HashMap<String, String> uploadImages(Map<String, String> srcImage, String studentId) {
        HashMap<String, String> imagePaths = new HashMap<>();
        for(var entry : srcImage.entrySet()) {
            String path = uploadImage(entry.getValue(), entry.getKey(), studentId);
            imagePaths.put(entry.getKey(), path);
        }
        return imagePaths;
    }
    
    @Override
    public String uploadImage(String filePath, String type, String studentId) { 
        try {
            File file = new File(filePath);
            String imageName = generateFileName(file.getName(), type);
            Map<String, String> map = new HashMap<>();
            map.put("firebaseStorageDownloadTokens", studentId + "/" + imageName);
            BlobId blobId = BlobId.of("backend-c4e58.appspot.com", studentId + "/" + imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setMetadata(map)
                    .setContentType(Files.probeContentType(Path.of(filePath)))
                    .build();
            BlobWriteOption blobWriteOption = BlobWriteOption.detectContentType();
            if(storage.get(blobId) == null) {
                blobInfo = storage.createFrom(blobInfo, new FileInputStream(file), blobWriteOption).asBlobInfo();
            }
            else {
                storage.update(blobInfo);
            }
            return "https://firebasestorage.googleapis.com/v0/b/"+ blobInfo.getBucket() +"/o/" + studentId + "%2F"+ imageName +"?alt=media";
        } catch (IOException ex) {
            return "";
        }
    }
    
    private String generateFileName(String originalFileName, String type) {
        return type + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}
