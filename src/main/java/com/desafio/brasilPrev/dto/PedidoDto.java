package com.desafio.brasilPrev.dto;

import java.util.List;

public class PedidoDto {

    public Long id;
    public Long cliente_id;
    public String sessao;
    public List<PedidoItemDto> pedidoItens;
}