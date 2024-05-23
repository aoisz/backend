/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.template;

/**
 *
 * @author PC
 */
public class TempCertificate {
    public CertificateFull full;
    public CertificateInformation information;
    public CertificateScore score;
    public String studentId;

    public CertificateFull getFull() {
        return full;
    }

    public void setFull(CertificateFull full) {
        this.full = full;
    }

    public CertificateInformation getInformation() {
        return information;
    }

    public void setInformation(CertificateInformation information) {
        this.information = information;
    }

    public CertificateScore getScore() {
        return score;
    }

    public void setScore(CertificateScore score) {
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
