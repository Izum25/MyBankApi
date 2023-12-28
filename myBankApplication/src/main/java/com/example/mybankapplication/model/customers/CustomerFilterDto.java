package com.example.mybankapplication.model.customers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerFilterDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String cif;
    private String phoneNumber;
}
