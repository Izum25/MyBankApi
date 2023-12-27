// AccountEntity.java
package com.example.mybankapplication.dao;

import com.example.mybankapplication.enumeration.AccountStatus;
import com.example.mybankapplication.enumeration.AccountType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchCode;
    private String accountNumber;
    private LocalDateTime accountOpenDate;
    private LocalDateTime accountExpireDate;
    private String iban;
    private String swift;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Column(name = "available_balance", columnDefinition = "NUMERIC(38,2) USING available_balance::numeric(38,2)")
    private BigDecimal availableBalance;
    @Column(name = "current_balance", columnDefinition = "NUMERIC(38,2) USING current_balance::numeric(38,2)")
    private BigDecimal currentBalance;
    @Column(name = "blocked_amount", columnDefinition = "NUMERIC(38,2) USING blocked_amount::numeric(38,2)")
    private BigDecimal blockedAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerEntity customer;

//    Interest Rate: For savings accounts, the interest rate associated with the account.
//    Overdraft Protection: Indicates whether the account has overdraft protection.
//    Transactions: A history of transactions associated with the account.
//    Withdrawal Limit: For certain accounts, a limit on the amount that can be withdrawn.
//    Transaction Limits: Limits on daily, weekly, or monthly transactions.
//    Card Information: If the account is associated with a debit or credit card, relevant information about the card.

}
