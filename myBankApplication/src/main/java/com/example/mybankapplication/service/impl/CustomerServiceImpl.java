package com.example.mybankapplication.service.impl;

import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.exception.DataAlreadyExistsException;
import com.example.mybankapplication.exception.NotDataFoundException;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.customers.CustomerResponse;
import com.example.mybankapplication.model.customers.CustomerRequest;
import com.example.mybankapplication.repository.CustomerRepository;
import com.example.mybankapplication.service.CustomerService;
import com.example.mybankapplication.specifications.CustomerSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Page<CustomerResponse> findCustomerByFilter(CustomerRequest filterDto, Pageable pageRequest) {
        log.debug("Searching information about customer by {}", filterDto.toString());

        Specification<CustomerEntity> customerSpecification = CustomerSpecifications.getCustomerSpecification(filterDto);
        Page<CustomerEntity> customerEntityPage = customerRepository.findAll(customerSpecification, pageRequest);

        log.info("Successfully search customer");
        return customerEntityPage.map(customerMapper::toDto);
    }

    protected void verifyId(Long id) throws NotDataFoundException {
        log.debug("Verifying customer by ID: {}", id);
        if (!customerRepository.existsById(id)) {
            log.error("Customer with ID {} not found", id);
            throw new NotDataFoundException("Customer with ID " + id + " not found");
        }
    }

    public List<CustomerResponse> getAllCustomer() {
        log.debug("Retrieving information about all customers");
        List<CustomerEntity> customerEntityList = customerRepository.findAll();

        List<CustomerResponse> customerDtoList = customerEntityList.stream()
                .map(customerMapper::toDto)
                .toList();
        log.info("Successfully retrieved all customers");
        return customerDtoList;
    }

    public CustomerResponse getCustomerById(Long id) {
        log.debug("Retrieving information for customer with ID: {}", id);
//        verifyId(id);

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new NotDataFoundException("Customer with ID not found"));

        CustomerResponse customerDto = customerMapper.toDto(customerEntity);
        log.info("Successfully retrieved customer with ID: {}", id);
        return customerDto;
    }

    public void updateCustomer(Long customerId, CustomerRequest customer) {
        log.debug("Updating customer with ID: {} to {}", customerId, customer);
        verifyId(customerId);
        for (CustomerResponse c : getAllCustomer()) {
            if (c.getId().equals(customerId)) {
                customerRepository.save(customerMapper.fromDto(customer));
                log.info("Customer updated successfully");
            }
        }
    }

    public void deleteCustomerById(Long id) {
        log.debug("Deleting customer with ID: {}", id);
        verifyId(id);
        customerRepository.deleteById(id);
        log.info("Successfully deleted customer with ID: {}", id);
    }

    public void addCustomerDto(CustomerRequest customer) {
        log.debug("Adding customer: {}", customer);
        if (customerRepository.existsById(customer.getId())) {
            log.error("Customer with ID {} is already exists", customer.getId());
            throw new DataAlreadyExistsException("Customer with ID is already exists");
        }
        customerRepository.save(customerMapper.fromDto(customer));
        log.info("Successfully added customer");
    }
}
