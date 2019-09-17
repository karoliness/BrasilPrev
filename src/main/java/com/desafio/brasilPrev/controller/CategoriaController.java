package com.desafio.brasilPrev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.desafio.brasilPrev.dto.CategoriaDto;
import com.desafio.brasilPrev.modelo.Categoria;
import com.desafio.brasilPrev.repository.CategoriaRepository;

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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private CategoriaDto mapearCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.id = categoria.getId();
        categoriaDto.nome = categoria.getNome();
        return categoriaDto;
    }

    @GetMapping
    public List<CategoriaDto> obterTodasAsCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream().map(this::mapearCategoriaDto).collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto.nome);
        categoriaRepository.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body("categoria criada com sucesso");
    }

    @PutMapping("/editar")
    public ResponseEntity editar(@RequestBody CategoriaDto categoriaDto) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaDto.id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.AlterarNome(categoriaDto.nome);
            categoriaRepository.save(categoria);

            return ResponseEntity.ok().body("categoria editada com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao alterar categoria");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().body("categoria excluída com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao deletar categoria");
    }

}