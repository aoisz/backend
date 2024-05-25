/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.Account;
import com.example.backend.model.AccountAdmin;
import com.example.backend.repository.AccountAdminRepository;
import com.example.backend.repository.AccountRepository;
import com.example.backend.template.AccountTemplate;
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
        String status = "notexist";
        if(StringUtils.isNumeric(input.getUsername())) {
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
        else {
            List<AccountAdmin> adminAccount = adminRepository.findAll();
            AccountAdmin adAccount = new AccountAdmin();
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
                    status = adAccount.getUsername();
                }
                else {
                    status = "error";
                }
            }
        }
        return status;
    }
}
