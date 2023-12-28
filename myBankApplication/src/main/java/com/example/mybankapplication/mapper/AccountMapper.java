package com.example.mybankapplication.mapper;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.model.accounts.AccountDto;
import com.example.mybankapplication.model.accounts.AccountFilterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, PassportMapper.class})
public interface AccountMapper {
    AccountDto mapToDto(AccountEntity accountEntity);
    AccountEntity mapToEntity(AccountDto accountDto);
    AccountFilterDto mapToFilterDto(AccountEntity accountEntity);
}
