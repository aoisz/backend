package com.example.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Date;

@Entity
public class StudentCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    
    @ManyToOne
    @JoinColumn(name="studentId", nullable = false, referencedColumnName = "studentId")
    private Students student; 
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image", referencedColumnName = "id")
    private CertificateImage images;
    
    @ManyToOne
    @JoinColumn(name="certificateId")
    private Certificates certificate;
    
    private String startDate;
    private String expiredDate;
    private String grade;
    private float totalScore;
    private float listeningScore;
    private float readingScore;
    
    @ManyToOne
    @JoinColumn(name="status")
    private CertificateStatus status;

    public StudentCertificate(int id, Students student, CertificateImage images, Certificates certificate, String startDate, String expiredDate, String grade, float totalScore, float listeningScore, CertificateStatus status) {
        this.id = id;
        this.student = student;
        this.images = images;
        this.certificate = certificate;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.grade = grade;
        this.totalScore = totalScore;
        this.listeningScore = listeningScore;
        this.status = status;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getListeningScore() {
        return listeningScore;
    }

    public void setListeningScore(float listeningScore) {
        this.listeningScore = listeningScore;
    }

    public float getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(float readingScore) {
        this.readingScore = readingScore;
    }

    public StudentCertificate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Certificates getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificates certificate) {
        this.certificate = certificate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public CertificateImage getImages() {
        return images;
    }

    public void setImages(CertificateImage images) {
        this.images = images;
    }

    public CertificateStatus getStatus() {
        return status;
    }

    public void setStatus(CertificateStatus status) {
        this.status = status;
    }
    
}
