package com.backendapp.faturabcknd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendapp.faturabcknd.model.Fatura;

public interface FaturaRepo extends JpaRepository<Fatura, Long> {

}
