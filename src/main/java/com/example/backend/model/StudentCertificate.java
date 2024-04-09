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
    private int score;

    public StudentCertificate(int id, Students student, Certificates certificate, Date startDate, Date expiredDate, String grade, int score) {
        this.id = id;
        this.student = student;
        this.certificate = certificate;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.grade = grade;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
