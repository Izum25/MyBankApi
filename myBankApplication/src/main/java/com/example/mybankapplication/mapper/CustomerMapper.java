package com.example.mybankapplication.mapper;

import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.model.customers.CustomerRequest;
import com.example.mybankapplication.model.customers.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "passport", target = "passport")
    CustomerResponse toDto(CustomerEntity customerEntity);

    CustomerEntity fromDto(CustomerRequest customerRequest);

    CustomerEntity fromDto(CustomerResponse customerResponse);

}
