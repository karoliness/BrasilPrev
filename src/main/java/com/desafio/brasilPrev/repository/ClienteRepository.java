package com.desafio.brasilPrev.repository;

import com.desafio.brasilPrev.modelo.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {    
}