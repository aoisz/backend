/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class CertificateImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String fullImage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String inforImage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String scoreImage;

    public CertificateImage() {
    }

    public CertificateImage(int id, String fullImage, String inforImage, String scoreImage) {
        this.id = id;
        this.fullImage = fullImage;
        this.inforImage = inforImage;
        this.scoreImage = scoreImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullImage() {
        return fullImage;
    }

    public void setFullImage(String fullImage) {
        this.fullImage = fullImage;
    }

    public String getInforImage() {
        return inforImage;
    }

    public void setInforImage(String inforImage) {
        this.inforImage = inforImage;
    }

    public String getScoreImage() {
        return scoreImage;
    }

    public void setScoreImage(String scoreImage) {
        this.scoreImage = scoreImage;
    }
    
    
}
