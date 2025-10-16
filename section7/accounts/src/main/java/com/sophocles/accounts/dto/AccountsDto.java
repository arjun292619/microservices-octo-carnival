package com.sophocles.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Customer and Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account number of Customer",
            example = "1546897587"
    )
    @NotEmpty(message = "account number field cannot be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "type of account",
            example = "savings"
    )
    @NotEmpty(message = "account type field cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Address of branch",
            example = "25th st, New York 57895"
    )
    @NotEmpty(message = "account branch address field cannot be null or empty")
    private String branchAddress;
}
