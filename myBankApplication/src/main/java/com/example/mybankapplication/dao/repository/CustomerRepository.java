package com.example.mybankapplication.dao.repository;

import com.example.mybankapplication.dao.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByFirstNameAndLastName(String fistName, String lastName);

    List<CustomerEntity> findByLastName(String lastName);

    List<CustomerEntity> findByFirstName(String firstName);

    List<CustomerEntity> findByBirthDate(LocalDate birthDate);

    List<CustomerEntity> findAll(Specification<CustomerEntity> specifications, Pageable pageable);

}