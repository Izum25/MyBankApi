package com.example.mybankapplication.service;

import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AccountService {

    Page<AccountResponse> findAccountsByFilter(AccountRequest accountRequest, Pageable pageRequest);

    List<AccountResponse> getAllAccounts();

    AccountResponse getAccountById(Long id);

    List<AccountResponse> getAllAccountsByCustomerId(Long id);

    void createAccount(AccountRequest account, Long customerId);

    void updateAccount(AccountRequest account, Long accountId);

    void deleteAccount(Long accountId);
}
