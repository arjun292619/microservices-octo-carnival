package com.sophocles.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer Details",
        description = "Schema to hold Customer, Account, Card, Loan information"
)
public class CustomerDetailsDto {
    @Schema(
            description = "Name of Customer",
            example = "John Wick"
    )
    @NotEmpty(message = "name field cannot be null or empty")
    @Size(min = 5, max = 30, message = "Customer length should be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "email of Customer",
            example = "johnwick@email.com"
    )
    @NotEmpty(message = "email field cannot be null or empty")
    @Email(message = "email should be valid")
    private String email;

    @Schema(
            description = "Phone number of Customer",
            example = "12358791234"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Card details of Customer"
    )
    private CardsDto cardsDto;

    @Schema(
            description = "Loan details of Customer"
    )
    private LoansDto loansDto;
}
