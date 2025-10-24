package com.sophocles.accounts.service.impl;

import com.sophocles.accounts.dto.AccountsDto;
import com.sophocles.accounts.dto.CardsDto;
import com.sophocles.accounts.dto.CustomerDetailsDto;
import com.sophocles.accounts.dto.LoansDto;
import com.sophocles.accounts.entity.Accounts;
import com.sophocles.accounts.entity.Customer;
import com.sophocles.accounts.exceptions.ResourceNotFoundException;
import com.sophocles.accounts.mapper.AccountsMapper;
import com.sophocles.accounts.mapper.CustomerMapper;
import com.sophocles.accounts.repository.AccountsRepository;
import com.sophocles.accounts.repository.CustomerRepository;
import com.sophocles.accounts.service.client.CardsFeignClient;
import com.sophocles.accounts.service.client.LoansFeignClient;
import com.sophocles.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
