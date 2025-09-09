package com.sophocles.accounts.service.impl;

import com.sophocles.accounts.dto.CustomerDto;
import com.sophocles.accounts.repository.AccountsRepository;
import com.sophocles.accounts.repository.CustomerRepository;
import com.sophocles.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
