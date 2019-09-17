package com.desafio.brasilPrev.repository;

import com.desafio.brasilPrev.modelo.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}