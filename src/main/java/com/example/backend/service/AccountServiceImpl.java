/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.Account;
import com.example.backend.repository.AccountRepository;
import com.example.backend.template.AccountTemplate;
import java.util.List;
import java.util.Optional;
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
        List<Account> accounts = repository.findAll();
        Account account = new Account();
        String status = null;
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
        return status;
    }
}
