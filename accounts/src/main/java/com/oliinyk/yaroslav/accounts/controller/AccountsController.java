package com.oliinyk.yaroslav.accounts.controller;

import com.oliinyk.yaroslav.accounts.constants.AccountsConstants;
import com.oliinyk.yaroslav.accounts.dto.CustomerDto;
import com.oliinyk.yaroslav.accounts.dto.ResponseDto;
import com.oliinyk.yaroslav.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
