package com.desafio.brasilPrev.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.brasilPrev.dto.CategoriaDto;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
    private String descricao;
    private String foto;

    public Produto() {
    }

    public Produto(Categoria categoria, String nome, BigDecimal preco, Integer quantidade,
     String descricao, String foto) {

        this.categoria = categoria;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

	public void AlterarCategoria(CategoriaDto categoria) {
        this.categoria.AlterarNome(categoria.nome);
	}

	public void AlterarNome(String nome) {
        this.nome = nome;
	}

	public void AlterarPreco(BigDecimal preco) {
        this.preco = preco;
	}

	public void AlterarQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
	}

	public void AlterarDescricao(String descricao) {
        this.descricao = descricao;
	}

	public void AlterarFoto(String foto) {
        this.foto = foto;
	}

}