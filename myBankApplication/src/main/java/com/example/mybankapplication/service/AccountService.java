package com.example.mybankapplication.service;

import com.example.mybankapplication.dao.AccountEntity;
import com.example.mybankapplication.dao.repository.AccountRepository;
import com.example.mybankapplication.exception.NotFoundException;
import com.example.mybankapplication.mapper.AccountMapper;
import com.example.mybankapplication.mapper.CustomerMapper;
import com.example.mybankapplication.model.accounts.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    public final AccountRepository accountRepository;
    public final AccountMapper accountMapper;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    protected void verifyId(Long id) throws NotFoundException {
        log.debug("Verifying account by ID: {}", id);
        if (!accountRepository.existsById(id)) {
            log.error("Account with ID {} not found", id);
            throw new NotFoundException("Account with ID " + id + " not found");
        }
    }

    public List<AccountDto> getAllAccounts() {
        log.debug("Retrieving all accounts");
        List<AccountEntity> accountEntityList = accountRepository.findAll();

        List<AccountDto> accountDtoList = accountEntityList.stream()
                .map(accountMapper::mapToDto)
                .toList();
        log.info("Successfully retrieved all accounts");
        return accountDtoList;
    }

    public AccountDto getAccountById(Long id) {
        log.debug("Retrieving account by ID: {}", id);
        verifyId(id);
        List<AccountDto> allAccounts = getAllAccounts();

        for (AccountDto account : allAccounts) {
            if (account.getId().equals(id)) {
                log.info("Successfully retrieved account");
                return account;
            }
        }
        log.warn("Account with ID {} not found", id);
        return null;
    }

    public List<AccountDto> getAllAccountsByCustomerId(Long id) {
        log.debug("Retrieving all accounts by customer ID: {}", id);
        customerService.verifyId(id);

        List<AccountDto> allAccounts = getAllAccounts();

        List<AccountDto> accountsForCustomer = new ArrayList<>();
        for (AccountDto account : allAccounts) {
            if (account.getCustomer().getId().equals(id)) {
                log.info("Successfully retrieved all accounts by customer ID: {}", id);
                accountsForCustomer.add(account);
            }
        }
        return accountsForCustomer;
    }

    public void createAccount(AccountDto account, Long customerId) {
        log.debug("Creating account for customer: {}", customerId);
        customerService.verifyId(customerId);

        AccountEntity accountEntity = accountMapper.mapToEntity(account);
        accountEntity.setCustomer(
                customerMapper.mapToEntity(
                        customerService.getCustomerById(customerId)
                )
        );

        accountRepository.save(accountEntity);
        log.info("Account created successfully");
    }


    public void updateAccount(AccountDto account, Long accountId) {
        log.debug("Updating account by ID: {}", accountId);

        verifyId(accountId);
        List<AccountDto> allAccounts = getAllAccounts();

        for (AccountDto a : allAccounts) {
            if (a.getId().equals(accountId)) {
                accountRepository.save(
                        accountMapper.mapToEntity(account));
                log.info("Account updated successfully");
            }
        }
    }

    public void deleteAccount(Long accountId) {
        log.debug("Deleting account by ID: {}", accountId);
        verifyId(accountId);
        for (AccountDto account : getAllAccounts()) {
            if (account.getId().equals(accountId)) {
                accountRepository.delete(
                        accountMapper.mapToEntity(account)
                );
                log.info("Account deleted successfully");
            }
        }
    }
}
