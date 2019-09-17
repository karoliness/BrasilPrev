package com.desafio.brasilPrev.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.brasilPrev.dto.EnderecoDto;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String nome, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

	public void AlterarNome(String nome) {
        this.nome = nome;
	}

	public void AlterarEmail(String email) {
        this.email = email;
	}

	public void AlterarSenha(String senha) {
        this.senha = senha;
    }
    
    public void AlterarEndereco(EnderecoDto enderecoDto){
       this.endereco.AlterarRua(enderecoDto.rua);
       this.endereco.AlterarBairro(enderecoDto.bairro);
       this.endereco.AlterarCidade(enderecoDto.cidade);
       this.endereco.AlterarCep(enderecoDto.cep);
       this.endereco.AlterarEstado(enderecoDto.estado);
    }

}