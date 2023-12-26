package com.example.mybankapplication.service;

import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.dao.repository.CustomerRepository;
import com.example.mybankapplication.exception.NotFoundException;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.customers.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

//    public Page<CustomerDto> searchCustomers(String firstName, String lastName, LocalDate birthDate, Pageable pageable) {
//        Page<Customer> customerPage;
//
//        if (!firstName.isEmpty() && !lastName.isEmpty()) {
//            customerPage = customerRepository.findByFirstNameContainingAndLastNameContainingAndBirthDateContaining(
//                    firstName, lastName, birthDate, pageable);
//        } else if (!birthDate.isEmpty()) {
//            customerPage = customerRepository.findByBirthDateContaining(birthDate, pageable);
//        } else {
//            // Обработка случая, если нет фильтров
//            return new PageImpl<>(Collections.emptyList(), pageable, 0);
//        }
//
//        List<CustomerDto> customerDtoList = customerPage.getContent()
//                .stream()
//                .map(customer -> modelMapper.map(customer, CustomerDto.class))
//                .collect(Collectors.toList());
//
//        return new PageImpl<>(customerDtoList, pageable, customerPage.getTotalElements());
//    }

//    public List<CustomerDto> getCustomers(String firstName, String lastName) {
//        log.info("Action.getLogCustomer start.");
//        if (firstName == null && lastName == null) {
//            List<CustomerEntity> customerEntities = (List<CustomerEntity>) customerRepository.findAll();
//            log.info("Action.getLogCustomer end.");
//            return customerEntities.stream().map(customerMapper::mapToDto)
//                    .collect(Collectors.toList());
//        } else {
//            return getCustomersByName(firstName, lastName);
//        }
//    }

//    public Page<CustomerDto> getCustomers(CustomerFilterDto customerFilterDto, Pageable pageable) {
//
//        var specifications = new CustomerSpecifications().getCustomerSpecification(customerFilterDto);
//
//
//        Page<CustomerEntity> customerEntityPage = (Page<CustomerEntity>) customerRepository
//                .findAll(specifications, pageable);
//
//        return customerEntityPage.map(customerMapper::mapToDto);
//    }
//
//    public List<CustomerDto> getCustomersByName(String firstName, String lastName) {
//        log.info("Action.getCustomerByName start");
//        List<CustomerEntity> customerEntities;
//
//        if (firstName == null) customerEntities = customerRepository.findByLastName(lastName);
//        else if (lastName == null) customerEntities = customerRepository.findByFirstName(firstName);
//        else customerEntities = customerRepository.findByFirstNameAndLastName(firstName, lastName);
//
//        if (customerEntities.isEmpty()) {
//            log.error("Customer " + firstName + " " + lastName + " not found.");
//            throw new RuntimeException("Customer " + firstName + " " + lastName + " not found.");
//        } else {
//            log.info("Action.getCustomerByName end");
//            return customerEntities.stream().map(customerMapper::mapToDto)
//                    .collect(Collectors.toList());
//        }
//    }
//
//    public CustomerDto getCustomerById(Long customerId) {
//        log.info("Action.getCustomer start.");
//        CustomerEntity customerEntity = customerRepository.findById(customerId)
//                .orElseThrow(() -> {
//                    log.error("Customer not found with id {}", customerId);
//                    return new NotFoundException("Customer not found with id " + customerId);
//                });
//        return customerMapper.mapToDto(customerEntity);
//    }
//
//    public void addCustomer(CustomerDto addCustomerDto) {
//        log.info("Action.addCustomer start.");
//        customerRepository.save(
//                customerMapper.mapToEntity(addCustomerDto));
//        log.info("Action.addCustomer end.");
//    }

    protected void verifyId(Long id) throws NotFoundException {
        log.debug("Verifying customer by ID: {}", id);
        if (!customerRepository.existsById(id)) {
            log.error("Customer with ID {} not found", id);
            throw new NotFoundException("Customer with ID " + id + " not found");
        }
    }

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
        customerRepository.save(customerMapper.mapToEntity(customer));
        log.info("Successfully added customer");
    }
}
