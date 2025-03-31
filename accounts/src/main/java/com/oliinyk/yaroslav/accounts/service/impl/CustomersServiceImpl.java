package com.oliinyk.yaroslav.accounts.service.impl;

import com.oliinyk.yaroslav.accounts.dto.AccountsDto;
import com.oliinyk.yaroslav.accounts.dto.CardsDto;
import com.oliinyk.yaroslav.accounts.dto.CustomerDetailsDto;
import com.oliinyk.yaroslav.accounts.dto.LoansDto;
import com.oliinyk.yaroslav.accounts.entity.Accounts;
import com.oliinyk.yaroslav.accounts.entity.Customer;
import com.oliinyk.yaroslav.accounts.exception.ResourceNotFoundException;
import com.oliinyk.yaroslav.accounts.mapper.AccountsMapper;
import com.oliinyk.yaroslav.accounts.mapper.CustomerMapper;
import com.oliinyk.yaroslav.accounts.repository.AccountsRepository;
import com.oliinyk.yaroslav.accounts.repository.CustomerRepository;
import com.oliinyk.yaroslav.accounts.service.ICustomersService;
import com.oliinyk.yaroslav.accounts.service.client.CardsFeignClient;
import com.oliinyk.yaroslav.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
