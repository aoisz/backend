/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.model.Account;
import com.example.backend.template.AccountTemplate;
import java.util.List;

/**
 *
 * @author phmlhuyntrang
 */
public interface AccountService {
    public List<Account> getAllAccount();
    public Account getById(int id);
    public String login(AccountTemplate account);
}
