package com.desafio.brasilPrev.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    public Endereco() {
    }

    public Endereco(String rua, String cidade, String bairro, String cep, String estado){
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }
    
    public String getRua() {
        return rua;
    }

	public void AlterarRua(String rua) {
        this.rua = rua;
	}

	public void AlterarBairro(String bairro) {
        this.bairro = bairro;
	}

	public void AlterarCidade(String cidade) {
        this.cidade = cidade;
	}

	public void AlterarCep(String cep) {
        this.cep = cep;
	}

	public void AlterarEstado(String estado) {
        this.estado = estado;
	}
}