package com.example.mybankapplication.service;

import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.exception.DataAlreadyExistsException;
import com.example.mybankapplication.exception.NotDataFoundException;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.customers.CustomerDto;
import com.example.mybankapplication.model.customers.CustomerFilterDto;
import com.example.mybankapplication.repository.CustomerRepository;
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
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Page<CustomerFilterDto> findCustomerByFilter(CustomerFilterDto filterDto, Pageable pageRequest) {
        log.debug("Searching information about customer by {}", filterDto.toString());

        Specification<CustomerEntity> customerSpecification = CustomerSpecifications.getCustomerSpecification(filterDto);
        Page<CustomerEntity> customerEntityPage = customerRepository.findAll(customerSpecification, pageRequest);

        log.info("Successfully search customer");
        return customerEntityPage.map(customerMapper::mapToFilterDto);
    }

    protected boolean verifyId(Long id) throws NotDataFoundException {
        log.debug("Verifying customer by ID: {}", id);
        if (!customerRepository.existsById(id)) {
            log.error("Customer with ID {} not found", id);
            throw new NotDataFoundException("Customer with ID " + id + " not found");
        }
        return true;
    }

//    public Page<CustomerFilterDto> findCustomersByFilter(CustomerFilterDto filterDto, Pageable pageable) {
//        log.debug("Searching information about customer by {}", filterDto.toString());
//        Page<CustomerEntity> customerPage = customerRepository.findAll(
//                Example.of(
//                        CustomerEntity.builder()
//                                .firstName(filterDto.getFirstName())
//                                .lastName(filterDto.getLastName())
//                                .birthDate(filterDto.getBirthDate())
//                                .email(filterDto.getEmail())
//                                .phoneNumber(filterDto.getPhoneNumber())
//                                .cif(filterDto.getCif())
//                                .build()
//                ),
//                pageable
//        );
//        Page<CustomerFilterDto> customerFilterDtoPage = customerPage.map(customerMapper::mapToFilterDto);
//        log.info("Successfully search customer");
//        return customerFilterDtoPage;
//    }

    public List<CustomerDto> getAllCustomer() {
        log.debug("Retrieving information about all customers");
        List<CustomerEntity> customerEntityList = customerRepository.findAll();

        List<CustomerDto> customerDtoList = customerEntityList.stream()
                .map(customerMapper::mapToDto)
                .toList();
        log.info("Successfully retrieved all customers");
        return customerDtoList;
    }

    public CustomerDto getCustomerById(Long id) {
        log.debug("Retrieving information for customer with ID: {}", id);
        verifyId(id);
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDto customerDto = customerMapper.mapToDto(customerEntity);
        log.info("Successfully retrieved customer with ID: {}", id);
        return customerDto;
    }

    public void updateCustomer(Long customerId, CustomerDto customer) {
        log.debug("Updating customer with ID: {} to {}", customerId, customer);
        verifyId(customerId);
        for (CustomerDto c : getAllCustomer()) {
            if (c.getId().equals(customerId)) {
                customerRepository.save(customerMapper.mapToEntity(customer));
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

    public void addCustomerDto(CustomerDto customer) {
        log.debug("Adding customer: {}", customer);
        if (verifyId(customer.getId())) {
            log.error("Customer with ID {} is already exists", customer.getId());
            throw new DataAlreadyExistsException("Customer with ID is already exists");
        }
        customerRepository.save(customerMapper.mapToEntity(customer));
        log.info("Successfully added customer");
    }
}
