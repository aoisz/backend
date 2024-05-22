package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class StudentCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    
    @ManyToOne
    @JoinColumn(name="studentId", nullable = false, referencedColumnName = "studentId")
    private Students student;
    
    @ManyToOne
    @JoinColumn(name="certificateId")
    private Certificates certificate;
    
    private Date startDate;
    private Date expiredDate;
    private String grade;
    private float totalScore;
    private float listeningScore;
    private float readingScore;
    private String status;

    public StudentCertificate(int id, Students student, Certificates certificate, Date startDate, Date expiredDate, String grade, float totalScore, float listeningScore, float readingScore, String status) {
        this.id = id;
        this.student = student;
        this.certificate = certificate;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.grade = grade;
        this.totalScore = totalScore;
        this.listeningScore = listeningScore;
        this.readingScore = readingScore;
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

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
