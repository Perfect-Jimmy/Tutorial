package com.tutorial.service.impl;

import com.tutorial.domain.Account;
import com.tutorial.repository.AccountRepository;
import com.tutorial.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy. 2018/3/26  15:15
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account queryById(Long id) {
        return accountRepository.getOne(id);
    }
}
