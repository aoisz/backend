/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.repository;

import com.example.backend.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phmlhuyntrang
 */
public interface AccountRepository extends JpaRepository<Account,Integer>{
    public List<Account> findAll();
}
