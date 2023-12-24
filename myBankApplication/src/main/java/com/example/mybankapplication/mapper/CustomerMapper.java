package com.example.mybankapplication.mapper;

import com.example.mybankapplication.dao.CustomerEntity;
import com.example.mybankapplication.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    @Mapping(source = "passport.personalNo", target = "passport.personalNumber")
    CustomerDto mapToDto(CustomerEntity customerEntity);

//    @Mapping(source = "passport.personalNumber", target = "passport.personalNo")
    CustomerEntity mapToEntity(CustomerDto customerDto);

}
