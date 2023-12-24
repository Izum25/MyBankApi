package com.example.mybankapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private PassportDto passport;
    private String email;
}
