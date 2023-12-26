package com.example.mybankapplication.model.customers;

import com.example.mybankapplication.model.PassportDto;
import com.example.mybankapplication.model.accounts.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private PassportDto passport;
    private List<AccountDto> accounts;
}
