package com.desafio.brasilPrev.repository;

import com.desafio.brasilPrev.modelo.PedidoItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>{

    
}