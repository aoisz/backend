/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.Account;
import com.example.backend.model.AccountAdmin;
import com.example.backend.model.Students;
import com.example.backend.repository.AccountAdminRepository;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.template.AccountTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phmlhuyntrang
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountAdminRepository adminRepository;
    @Autowired
    private StudentService stdnService;

    @Override
    public List<Account> getAllAccount() {
        return repository.findAll();
    }

    @Override
    public Account getById(int id) {
        return null;
    }

    @Override
    public String login(AccountTemplate input) {
        String status = isAdmin(input);
        if (status.equals("admin")) {
            return status;
        }
        else {
            List<Account> accounts = repository.findAll();
            Account account = new Account();
            boolean accountExist = false;
            for(Account acc : accounts) {
                if(acc.getUsername().equals(input.getUsername())) {
                    accountExist = true;
                    account = acc;
                    break;
                }
            }
            if(accountExist) {
                if (account.getPassword().equals(input.getPassword())) {
                    status = account.getUsername();
                }
                else {
                    status = "error";
                }
            } 
        }
        return status;
    }
    public String isAdmin(AccountTemplate input) {
        List<AccountAdmin> adminAccount = adminRepository.findAll();
        AccountAdmin adAccount = new AccountAdmin();
        String status = "notexist";
        boolean accountExist = false;
        for(AccountAdmin acc : adminAccount) {
            if(input.getUsername().equals(acc.getUsername())) {
                adAccount = acc;
                accountExist = true;
                break;
            }
        }
        if(accountExist) {
            if (adAccount.getPassword().equals(input.getPassword())) {
                status = "admin";
            }
            else {
                status = "error";
            }
        }
        return status;
    }
    
    @Override
    public String changePassword(String studentId, String oldPwd, String newPwd) {
        if(oldPwd.equals(newPwd)) {
            return "same";
        }
        List<Account> accounts = repository.findAll();
        Account account = new Account();
        for(Account acc : accounts) {
            if(studentId.equals(acc.getUsername())) {
                account = acc;
            }
        }
        if(!account.getPassword().equals(oldPwd)) {
            return "old_wrong";
        }
        account.setPassword(newPwd);
        repository.save(account);
        return "updated";
    }
    
    @Override
    public List<Account> getByStudent(String studentId) {
        List<Account> accounts = repository.findAll();
        List<Account> acc = new ArrayList<>();
        for(Account account : accounts) {
            if(account.getUsername().contains(studentId)) {
                acc.add(account);
            }
        }
        return acc;
    }
    
    @Override
    public boolean resetPassword(String studentId) {
        Students student = stdnService.getByStudentId(studentId);
//        String dob = student.getDateOfBirth();
//        dob = dob.replace("/", "");
//        List<Account> accounts = repository.findAll();
//        Account account = null;
//        for(Account acc : accounts) {
//            if(acc.getUsername().contains(studentId)) {
//                account = acc;
//                break;
//            }
//        }
//        account.setPassword(dob);
//        repository.save(account);
        return true;
    }
}
