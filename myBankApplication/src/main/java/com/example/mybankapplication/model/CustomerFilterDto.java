package com.example.mybankapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilterDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
