package com.example.mybankapplication.controller;

import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import com.example.mybankapplication.model.customers.CustomerResponse;
import com.example.mybankapplication.model.customers.CustomerRequest;
import com.example.mybankapplication.service.AccountService;
import com.example.mybankapplication.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final AccountService accountService;

    @GetMapping("/search")
    public ResponseEntity<Page<CustomerResponse>> getCustomersByFilter(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cif,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Received GET request for searching customer");
        CustomerRequest filterDto = CustomerRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .email(email)
                .cif(cif)
                .phoneNumber(phoneNumber)
                .build();

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<CustomerResponse> customerPage = customerService.findCustomerByFilter(filterDto, pageRequest);
        return ResponseEntity.ok(customerPage);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@Validated @RequestBody CustomerRequest customer) {
        log.info("Received POST request for adding customer " + customer.toString());
        customerService.addCustomerDto(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customer) {
        log.info("Received PUT request for updating customer with id: " + id + " to " + customer.toString());
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        log.info("Received DELETE request for deleting customer with id: " + id);
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        log.info("Received GET request for getting customer with id: " + id);
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        log.info("Received GET request for getting all customers");
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccountsByCustomerId(@PathVariable Long customerId) {
        log.info("Received GET request for getting all accounts for customer with id: " + customerId);
        return new ResponseEntity<>(accountService.getAllAccountsByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest account, @PathVariable Long customerId) {
        log.info("Received POST request for creating account to a customer with id: " + customerId);
        accountService.createAccount(account, customerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
