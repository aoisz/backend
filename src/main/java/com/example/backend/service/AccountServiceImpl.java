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
        String status = "";
        boolean accountExist = false;
        for(Account account : accounts) {
            if(account.getUsername().equals(input.getUsername())) {
                accountExist = true;
            }
        }
        if(!accountExist) {
            status = "notfound";
        }
        else {
            Optional<Account> optional = accounts
                    .stream()
                    .filter(account -> account.getPassword().equals(input.getPassword()))
                    .findFirst();
            if(optional.isPresent()) {
                status = "found";
            }
            else {
                status = "error";
            }
        }
        return status;
    }
}
