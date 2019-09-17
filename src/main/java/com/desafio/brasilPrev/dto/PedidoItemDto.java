package com.desafio.brasilPrev.dto;

import java.math.BigDecimal;

public class PedidoItemDto {

    public Long id;
    public Long pedido_id;
    public Long produto_id;
    public String item;
    public Integer quantidade;
    public BigDecimal valorUnitario;
}