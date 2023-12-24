package com.example.mybankapplication.service;

import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.dao.repository.CustomerRepository;
import com.example.mybankapplication.exception.NotFoundException;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.CustomerDto;
import com.example.mybankapplication.model.CustomerFilterDto;
import com.example.mybankapplication.specifications.CustomerSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<CustomerDto> getCustomers(CustomerFilterDto customerFilterDto, Pageable pageable) {

        var specifications = new CustomerSpecifications().getCustomerSpecification(customerFilterDto);


        Page<CustomerEntity> customerEntityPage = (Page<CustomerEntity>) customerRepository
                .findAll(specifications, pageable);

        return customerEntityPage.map(customerMapper::mapToDto);
    }

    public List<CustomerDto> getCustomersByName(String firstName, String lastName) {
        log.info("Action.getCustomerByName start");
        List<CustomerEntity> customerEntities;

        if (firstName == null) customerEntities = customerRepository.findByLastName(lastName);
        else if (lastName == null) customerEntities = customerRepository.findByFirstName(firstName);
        else customerEntities = customerRepository.findByFirstNameAndLastName(firstName, lastName);

        if (customerEntities.isEmpty()) {
            log.error("Customer " + firstName + " " + lastName + " not found.");
            throw new RuntimeException("Customer " + firstName + " " + lastName + " not found.");
        } else {
            log.info("Action.getCustomerByName end");
            return customerEntities.stream().map(customerMapper::mapToDto)
                    .collect(Collectors.toList());
        }
    }
//    public List<CustomerDto> getCustomerByFirstName(){
//                return customerRepository.findAll(fistName);
//    }

    public CustomerDto getCustomer(Integer customerId) {
        log.info("Action.getCustomer start.");
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.error("Customer not found with id {}", customerId);
                    return new NotFoundException("Customer not found with id " + customerId);
                });
        return customerMapper.mapToDto(customerEntity);
    }

    public void addCustomer(CustomerDto addCustomerDto) {
        log.info("Action.addCustomer start.");
        customerRepository.save(
                customerMapper.mapToEntity(addCustomerDto));
        log.info("Action.addCustomer end.");
    }

    public void updateCustomer(Integer customerId, CustomerDto updateCustomerDto) {
        log.info("Action.updateCustomer start.");
        if (customerRepository.findById(customerId).isPresent()) {
            customerRepository.save(
                    customerMapper.mapToEntity(updateCustomerDto)
            );
            log.info("Action.updateCustomer end.");
        } else {
            log.error("Customer not found with id {}", customerId);
            throw new RuntimeException("Customer not found with id " + customerId);
        }
    }

    public void deleteCustomer(Integer customerId) {
        log.info("Action.deleteCustomer start.");
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            log.info("Action.deleteCustomer end.");
        } else {
            log.error("Customer not found with id {}", customerId);
            throw new RuntimeException("Customer not found with id " + customerId);
        }
    }
}
