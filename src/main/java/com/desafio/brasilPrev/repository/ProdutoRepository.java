package com.desafio.brasilPrev.repository;

import com.desafio.brasilPrev.modelo.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    
}