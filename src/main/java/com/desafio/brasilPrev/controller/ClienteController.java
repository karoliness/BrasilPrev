package com.desafio.brasilPrev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.desafio.brasilPrev.dto.ClienteDto;
import com.desafio.brasilPrev.dto.EnderecoDto;
import com.desafio.brasilPrev.modelo.Cliente;
import com.desafio.brasilPrev.modelo.Endereco;
import com.desafio.brasilPrev.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    private Endereco mapearEndereco(EnderecoDto enderecoDto) {
        Endereco endereco = new Endereco(enderecoDto.rua, enderecoDto.cidade, enderecoDto.bairro, enderecoDto.cep,
                enderecoDto.estado);
        return endereco;
    }

    public EnderecoDto mapearEnderecoDto(Endereco endereco) {
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.id = endereco.getId();
        enderecoDto.rua = endereco.getRua();
        enderecoDto.bairro = endereco.getBairro();
        enderecoDto.cidade = endereco.getCidade();
        enderecoDto.estado = endereco.getEstado();
        enderecoDto.cep = endereco.getCep();
    
        return enderecoDto;
    }

    public ClienteDto mapearClienteDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        EnderecoDto enderecoDto = mapearEnderecoDto(cliente.getEndereco());

        clienteDto.id = cliente.getId();
        clienteDto.nome = cliente.getNome();
        clienteDto.email = cliente.getEmail();
        clienteDto.endereco = enderecoDto;
        clienteDto.senha = cliente.getSenha();

        return clienteDto;
    }

    @GetMapping
    public List<ClienteDto> obterTodosOsClientes(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(this::mapearClienteDto).collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public ResponseEntity criar(@Valid @RequestBody ClienteDto clienteDto) {
        Endereco endereco = mapearEndereco(clienteDto.endereco);
        Cliente cliente = new Cliente(clienteDto.nome, clienteDto.email, clienteDto.senha, endereco);
        clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body("cliente adicionado com sucesso");
    }

    @PutMapping("/editar")
    public ResponseEntity editar(@RequestBody ClienteDto clienteDto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDto.id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.AlterarNome(clienteDto.nome);
            cliente.AlterarEmail(clienteDto.email);
            cliente.AlterarSenha(clienteDto.senha);
            cliente.AlterarEndereco(clienteDto.endereco);
            clienteRepository.save(cliente);

            return ResponseEntity.ok().body("cliente alterado com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao alterar cliente");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().body("cliente excluído com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao excluir cliente");
    }
}