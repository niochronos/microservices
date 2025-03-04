package com.oliinyk.yaroslav.accounts.service.impl;

import com.oliinyk.yaroslav.accounts.constants.AccountsConstants;
import com.oliinyk.yaroslav.accounts.dto.CustomerDto;
import com.oliinyk.yaroslav.accounts.entity.Accounts;
import com.oliinyk.yaroslav.accounts.entity.Customer;
import com.oliinyk.yaroslav.accounts.exception.CustomerAlreadyExistsException;
import com.oliinyk.yaroslav.accounts.mapper.CustomerMapper;
import com.oliinyk.yaroslav.accounts.repository.AccountsRepository;
import com.oliinyk.yaroslav.accounts.repository.CustomerRepository;
import com.oliinyk.yaroslav.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobile number " + customerDto.getMobileNumber()
            );
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100000000000L + new Random().nextInt(900000000);

        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setCreatedAt(LocalDateTime.now());
        newAccounts.setCreatedBy("Anonymous");
        return newAccounts;
    }
}
