package com.sophocles.accounts.service;

import com.sophocles.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     * @param mobileNumber - Input mobileNymber
     * @return Customer details based on provided mobiel number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
