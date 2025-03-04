package com.oliinyk.yaroslav.accounts.service;

import com.oliinyk.yaroslav.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
}
