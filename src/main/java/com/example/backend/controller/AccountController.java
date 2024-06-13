/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.controller;

import com.example.backend.service.AccountService;
import com.example.backend.model.Account;
import com.example.backend.template.AccountTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phmlhuyntrang
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService service;
    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return service.getAllAccount();
    }
    @PostMapping("/login")
    public String login(@RequestBody AccountTemplate account) {
        return service.login(account);
    }
    
    @PostMapping("/change_password")
    public String changePassword(@RequestParam("studentId") String studentId, @RequestParam("newPwd") String newPwd, @RequestParam("oldPwd") String oldPwd) {
        return service.changePassword(studentId, oldPwd, newPwd);
    }
    
    @PostMapping("/search")
    public List<Account> search(@RequestParam("studentId") String studentId) {
        return service.getByStudent(studentId);
    }
    
    @PostMapping("/reset")
    public boolean resetPassword(@RequestParam("studentId") String studentId) {
        return service.resetPassword(studentId);
    }
}
