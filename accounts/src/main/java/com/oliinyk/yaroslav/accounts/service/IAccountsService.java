package com.oliinyk.yaroslav.accounts.service;

import com.oliinyk.yaroslav.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - CustomerDto Object
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indication if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber - Input Account Number
     * @return boolean indication if the update communication status is successful or not
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
