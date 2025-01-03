package com.example.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.BankAccounts;

@Repository
public interface BankRepository extends JpaRepository<BankAccounts, Integer>{
	Optional<BankAccounts> findByUpiId(String upiId);
}

