package com.desafio.brasilPrev.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataDeCriacao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    private Situacao situacao;
    private String sessao;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PedidoItem> pedidoItens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente, List<PedidoItem> pedidoItens, String sessao) {
        this.dataDeCriacao = LocalDateTime.now();
        this.cliente = cliente;
        this.situacao = Situacao.ANDAMENTO;
        this.sessao = sessao;
        this.pedidoItens = pedidoItens.stream().map(this::inserir).collect(Collectors.toList());
    }

    private PedidoItem inserir(PedidoItem pedidoItem) {
        pedidoItem.setPedido(this);
        return pedidoItem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getSessao() {
        return sessao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void Concluido() {
        this.situacao = Situacao.CONCLUIDO;
    }

    // public void Cancelado() {
    //     this.situacao = Situacao.CANCELADO;
    // }

    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void AlterarSessao(String sessao) {
        this.sessao = sessao;
    }

	public void Alterar(List<PedidoItem> pedidoItens2) {
       this.pedidoItens =  pedidoItens2.stream().map(this::inserir).collect(Collectors.toList());
	}

}