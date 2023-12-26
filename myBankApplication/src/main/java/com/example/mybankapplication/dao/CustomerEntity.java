package com.example.mybankapplication.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    //    @JsonManagedReference
    private PassportEntity passport;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    List<AccountEntity> accounts;
}
