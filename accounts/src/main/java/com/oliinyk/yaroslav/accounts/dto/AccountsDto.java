package com.oliinyk.yaroslav.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "AccountNumber can not be an empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountType  can not be an empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress  can not be an empty")
    private String branchAddress;
}
