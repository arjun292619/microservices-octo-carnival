package com.sophocles.accounts.controller;

import com.sophocles.accounts.constants.AccountsConstants;
import com.sophocles.accounts.dto.CustomerDto;
import com.sophocles.accounts.dto.ResponseDto;
import com.sophocles.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST apis for accounts in Bank microservice",
        description = "CRUD REST apis for accounts in Bank microservice to CREATE, READ, UPDATE and DELETE"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account REST api",
            description = "REST api to create a new customer for Bank"
    )
    @ApiResponse(responseCode = "201",
            description = "HTTP Status CREATED")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account details REST api",
            description = "REST api to fetch customer & account details for Bank"
    )
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account details REST api",
            description = "REST api to update customer & account details for Bank"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",
                    description = "HTTP Status Internal Server Error")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500)
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Delete Account details REST api",
            description = "REST api to delete customer & account details for Bank"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",
                    description = "HTTP Status Internal Server Error")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.STATUS_500));
        }
    }
}
