package com.example.mybankapplication.model.accounts;

import com.example.mybankapplication.entities.CustomerEntity;
import com.example.mybankapplication.enumeration.AccountStatus;
import com.example.mybankapplication.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String branchCode;
    private String accountNumber;
    private LocalDate accountOpenDate;
    private LocalDateTime accountExpireDate;
    private String iban;
    private String swift;
    private String currency;
    private AccountType accountType;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal blockedAmount;
    private CustomerEntity customer;
}
