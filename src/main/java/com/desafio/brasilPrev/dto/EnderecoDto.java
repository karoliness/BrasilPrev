package com.desafio.brasilPrev.dto;

import javax.validation.constraints.NotBlank;

public class EnderecoDto {

    public Long id;
    @NotBlank(message = "digite o nome da rua")
    public String rua;
    @NotBlank(message = "digite o nome da cidade")
    public String cidade;
    @NotBlank(message = "digite o nome do bairro")
    public String bairro;
    public String cep;
    @NotBlank(message = "digite o estado:  Exemplo: SP")
    public String estado;
}