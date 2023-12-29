package com.example.mybankapplication.service;

import com.example.mybankapplication.model.customers.CustomerRequest;
import com.example.mybankapplication.model.customers.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Page<CustomerResponse> findCustomerByFilter(CustomerRequest filterDto, Pageable pageRequest);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse getCustomerById(Long id);
    void updateCustomer(Long customerId, CustomerRequest customer);
    void deleteCustomerById(Long id);
    void addCustomerDto(CustomerRequest customer);

    }
