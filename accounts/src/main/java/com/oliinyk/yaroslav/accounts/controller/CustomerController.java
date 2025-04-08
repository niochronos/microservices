package com.oliinyk.yaroslav.accounts.controller;

import com.oliinyk.yaroslav.accounts.dto.CustomerDetailsDto;
import com.oliinyk.yaroslav.accounts.dto.ErrorResponseDto;
import com.oliinyk.yaroslav.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customers Bank",
        description = "REST API for Bank to customer details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomersService customersService;

    public CustomerController(ICustomersService customersService) {
        this.customersService = customersService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader("bank-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            @RequestParam String mobileNumber
    ) {
        logger.debug("Start method fetchCustomerDetails()");
        CustomerDetailsDto customerDetailsDto = customersService.fetchCustomerDetails(mobileNumber, correlationId);
        logger.debug("End method fetchCustomerDetails()");
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
