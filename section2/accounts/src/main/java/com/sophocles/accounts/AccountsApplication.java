package com.sophocles.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(title = "Accounts Microservice REST api Documentation",
                description = "Accounts Microservice REST api Documentation",
                version = "V1",
                contact = @Contact(
                        name = "Arjun",
                        email = "accountsMs@email.com")
        ),
        externalDocs = @ExternalDocumentation(
                description = "Accounts Microservice REST api Documentation"
        ))
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
