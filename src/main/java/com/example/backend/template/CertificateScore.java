/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.template;

/**
 *
 * @author PC
 */
public class CertificateScore {
    public String imageURL = "";
    public int listeningScore = 0;
    public int readingScore = 0;
    public int totalScore = 0;

    public CertificateScore() {
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getListeningScore() {
        return listeningScore;
    }

    public void setListeningScore(int listeningScore) {
        this.listeningScore = listeningScore;
    }

    public int getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(int readingScore) {
        this.readingScore = readingScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    
}
