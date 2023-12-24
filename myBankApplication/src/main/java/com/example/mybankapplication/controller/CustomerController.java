package com.example.mybankapplication.controller;

import com.example.mybankapplication.model.CustomerDto;
import com.example.mybankapplication.model.CustomerFilterDto;
import com.example.mybankapplication.service.CustomerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @GetMapping
//    public List<CustomerDto> getCustomers(@RequestParam(required = false) String firstName,
//                                          @RequestParam(required = false) String lastName) {
//        return customerService.getCustomers(firstName, lastName);
//    }
    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerDto addCustomerDto) {
        customerService.addCustomer(addCustomerDto);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody CustomerDto updateCustomerDto) {
        customerService.updateCustomer(customerId, updateCustomerDto);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping
    public Page<CustomerDto> getCustomers(CustomerFilterDto customerFilterDto, Pageable pageable){
        return customerService.getCustomers(customerFilterDto, pageable);
    }
}
