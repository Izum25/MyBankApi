package com.example.mybankapplication.controller;

import com.example.mybankapplication.enumeration.AccountStatus;
import com.example.mybankapplication.enumeration.AccountType;
import com.example.mybankapplication.model.accounts.AccountDto;
import com.example.mybankapplication.model.accounts.AccountFilterDto;
import com.example.mybankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/search")
    public ResponseEntity<Page<AccountFilterDto>> getAccountByFilter(
            @RequestParam(required = false) String branchCode,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) LocalDate accountOpenDate,
            @RequestParam(required = false) LocalDateTime accountExpireDate,
            @RequestParam(required = false) String iban,
            @RequestParam(required = false) String swift,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) AccountType accountType,
            @RequestParam(required = false) AccountStatus status,
            @RequestParam(required = false) BigDecimal availableBalance,
            @RequestParam(required = false) BigDecimal currentBalance,
            @RequestParam(required = false) BigDecimal blockedAmount,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        log.info("Received GET request for searching accounts");

        // Create the filter DTO
        AccountFilterDto filterDto = AccountFilterDto.builder()
                .branchCode(branchCode)
                .accountNumber(accountNumber)
                .accountOpenDate(accountOpenDate)
                .accountExpireDate(accountExpireDate)
                .iban(iban)
                .swift(swift)
                .currency(currency)
                .accountType(accountType)
                .status(status)
                .availableBalance(availableBalance)
                .currentBalance(currentBalance)
                .blockedAmount(blockedAmount)
                .build();

        // Create page request with sorting
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

        // Retrieve the paginated results using the service
        Page<AccountFilterDto> accountPage = accountService.findAccountsByFilter(filterDto, pageRequest);

        // Return the paginated results along with HTTP status
        return ResponseEntity.ok(accountPage);
    }

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
