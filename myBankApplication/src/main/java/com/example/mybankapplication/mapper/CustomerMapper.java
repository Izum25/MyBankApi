package com.example.mybankapplication.mapper;

import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.model.customers.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "passport", target = "passport")
    CustomerDto mapToDto(CustomerEntity customerEntity);

    CustomerEntity mapToEntity(CustomerDto customerDto);

}
