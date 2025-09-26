package com.sophocles.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold successful error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "error code of the message"
    )
    private String apiPath;
    @Schema(
            description = "error path"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "error message"
    )
    private String errorMessage;

    @Schema(
            description = " date time of error"
    )
    private LocalDateTime errorDT;
}
