package com.example.mybankapplication.mapper;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, PassportMapper.class})
public interface AccountMapper {
    AccountResponse toDto(AccountEntity accountEntity);
    AccountEntity fromDto(AccountRequest accountRequest);
}
