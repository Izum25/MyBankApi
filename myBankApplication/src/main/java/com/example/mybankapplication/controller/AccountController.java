package com.example.mybankapplication.controller;

import com.example.mybankapplication.model.accounts.AccountDto;
import com.example.mybankapplication.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        log.info("Received GET request for getting all accounts");
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountId) {
        log.info("Received GET request for getting account by ID: {}", accountId);
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDto account, @PathVariable Long accountId) {
        log.info("Received UPDATE request for update account by ID: {}", accountId);
        accountService.updateAccount(account, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        log.info("Received DELETE request for delete account by ID: {}", accountId);
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
