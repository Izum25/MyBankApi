package com.example.mybankapplication.dao.repository;

import com.example.mybankapplication.dao.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    public List<AccountEntity> findByCustomerId(Integer customer_id);
}
