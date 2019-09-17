package com.desafio.brasilPrev.repository;

import com.desafio.brasilPrev.modelo.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    
}