package com.oliinyk.yaroslav.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "AccountNumber can not be an empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Account Number of Bank account",
            example = "1234567890"
    )
    private Long accountNumber;

    @NotEmpty(message = "AccountType  can not be an empty")
    @Schema(
            description = "Account type of Bank account",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "BranchAddress  can not be an empty")
    @Schema(
            description = "Bank branch address",
            example = "123 NewYork"
    )
    private String branchAddress;
}
