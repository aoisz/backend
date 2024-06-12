/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.backend.service;

import java.util.Map;
import java.util.HashMap;

public interface ImageService {
    public String uploadImage(String filePath, String type, String studentId);
    public HashMap<String, String> uploadImages(Map<String, String> srcImage, String studentId);
}
