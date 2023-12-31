package com.example.mybankapplication.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "passports")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "personal_no", nullable = false, unique = true)
    private String personalNo;
    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
//    @JsonBackReference
    @JsonManagedReference
    private CustomerEntity customer;
}