package com.BankingAPI.repositories;

import com.BankingAPI.models.Analista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalistaRepository extends JpaRepository<Analista, Long> {
}
