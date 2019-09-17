package com.desafio.brasilPrev.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subtotal;

    public PedidoItem() {
    }

    public PedidoItem(Produto produto, Integer quantidade, BigDecimal valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    } 

    public Long getId() {
        return id;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

	public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}