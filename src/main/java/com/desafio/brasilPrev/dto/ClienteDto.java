package com.desafio.brasilPrev.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.desafio.brasilPrev.dto.EnderecoDto;

public class ClienteDto {

    public Long id;
    @NotBlank(message = "nome do cliente não pode ser vazio ou nulo")
    public String nome;
    @Email(message = "example@example")
    public String email;
    @NotBlank(message = "senha não pode ser nulo ou vazio")
    public String senha;
    @NotNull
    public EnderecoDto endereco;

}