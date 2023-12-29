package com.example.mybankapplication.model.customers;

import com.example.mybankapplication.model.PassportDto;
import com.example.mybankapplication.model.accounts.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String cif;
    private String phoneNumber;
    private PassportDto passport;
    private List<AccountRequest> accounts;
}
