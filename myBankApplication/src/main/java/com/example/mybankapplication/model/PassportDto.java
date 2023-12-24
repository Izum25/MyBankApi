package com.example.mybankapplication.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassportDto {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String personalNumber;
    private LocalDate expiredDate;
//    private String patronymic;
//    private String sex;
//    private String nationality;
//    private CustomerDto customer;
}
