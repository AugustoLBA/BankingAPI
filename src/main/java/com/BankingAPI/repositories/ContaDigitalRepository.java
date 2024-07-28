package com.BankingAPI.repositories;

import com.BankingAPI.models.ContaDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaDigitalRepository extends JpaRepository<ContaDigital, Long> {
}
