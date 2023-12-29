package com.example.mybankapplication.service.impl;

import com.example.mybankapplication.entities.AccountEntity;
import com.example.mybankapplication.exception.NotDataFoundException;
import com.example.mybankapplication.mapper.AccountMapper;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.accounts.AccountRequest;
import com.example.mybankapplication.model.accounts.AccountResponse;
import com.example.mybankapplication.model.customers.CustomerResponse;
import com.example.mybankapplication.repository.AccountRepository;
import com.example.mybankapplication.service.AccountService;
import com.example.mybankapplication.specifications.AccountSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    public final AccountRepository accountRepository;
    public final AccountMapper accountMapper;
    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;

    public Page<AccountResponse> findAccountsByFilter(AccountRequest accountRequest, Pageable pageRequest) {
        Specification<AccountEntity> accountSpecification = AccountSpecifications.getAccountSpecification(accountRequest);
        Page<AccountEntity> accountEntityPage = accountRepository.findAll(accountSpecification, pageRequest);
        return accountEntityPage.map(accountMapper::toDto);
    }

//    protected boolean verifyId(Long id) {
//        log.debug("Verifying account by ID: {}", id);
//        if (!accountRepository.existsById(id)) {
//            log.error("Account with ID {} not found", id);
//            throw new NotDataFoundException("Account with ID " + id + " not found");
//        }
//        return true;
//    }

    public List<AccountResponse> getAllAccounts() {
        log.debug("Retrieving all accounts");

        List<AccountEntity> accountEntityList = accountRepository.findAll();
        List<AccountResponse> accountDtoList = accountEntityList.stream()
                .map(accountMapper::toDto)
                .toList();

        log.info("Successfully retrieved all accounts");
        return accountDtoList;
    }

    public AccountResponse getAccountById(Long id) {
        log.debug("Retrieving account by ID: {}", id);

        AccountEntity accountEntity = accountRepository.findById(id)
                .orElseThrow(() -> new NotDataFoundException("Account with ID " + id + " not found"));

        AccountResponse accountResponse = accountMapper.toDto(accountEntity);

        log.info("Successfully retrieved account");
        return accountResponse;
    }

    public List<AccountResponse> getAllAccountsByCustomerId(Long id) { //customerId
        log.debug("Retrieving all accounts by customer ID: {}", id);
        customerService.verifyId(id);

        CustomerResponse customerResponseList = customerService.getCustomerById(id);
        List<AccountResponse> accountResponses = customerResponseList.getAccounts();

        log.info("Successfully retrieved all accounts by customer ID: {}", id);
        return accountResponses;
    }

    public void createAccount(AccountRequest account, Long customerId) {
        log.debug("Creating account for customer: {}", customerId);

        customerService.verifyId(customerId);
        AccountEntity accountEntity = accountMapper.fromDto(account);
        accountEntity.setCustomer(customerMapper.fromDto(customerService.getCustomerById(customerId)));
        accountRepository.save(accountEntity);

        log.info("Account created successfully");
    }


    public void updateAccount(AccountRequest account, Long accountId) {
        log.debug("Updating account by ID: {}", accountId);

        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotDataFoundException("Account with ID " + accountId + " not found"));
        accountRepository.save(accountEntity);

        log.info("Account updated successfully");
    }

    public void deleteAccount(Long accountId) {
        log.debug("Deleting account by ID: {}", accountId);

        accountRepository.findById(accountId)
                .orElseThrow(() -> new NotDataFoundException("Account with ID " + accountId + " not found"));
        accountRepository.deleteById(accountId);

        log.info("Account deleted successfully");
    }
}

