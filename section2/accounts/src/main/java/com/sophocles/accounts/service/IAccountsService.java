package com.sophocles.accounts.service;

import com.sophocles.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto object
    **/

    void createAccount(CustomerDto customerDto);
}
