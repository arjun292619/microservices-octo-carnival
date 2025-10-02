package com.sophocles.accounts.controller;

import com.sophocles.accounts.constants.AccountsConstants;
import com.sophocles.accounts.dto.CustomerDto;
import com.sophocles.accounts.dto.ErrorResponseDto;
import com.sophocles.accounts.dto.ResponseDto;
import com.sophocles.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;

    @Value("${build.version}")
    public String buildVersion;

    @Autowired
    private Environment environment;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

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
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )),
            @ApiResponse(responseCode = "417",
                    description = "Expectation Failed"),
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE)
                    , HttpStatus.EXPECTATION_FAILED);
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_417_DELETE, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Fetch build information REST api",
            description = "REST api to fetch build information"
    )
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Fetch Java version used for REST api",
            description = "REST api to fetch Java version used for application"
    )
    @ApiResponse(responseCode = "200",
            description = "HTTP Status OK")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("MAVEN_HOME"));
    }
}
