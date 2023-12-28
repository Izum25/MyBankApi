package com.example.mybankapplication.repository;

import com.example.mybankapplication.entities.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Page<AccountEntity> findAll(Specification<AccountEntity> spec, Pageable pageRequest);
}
