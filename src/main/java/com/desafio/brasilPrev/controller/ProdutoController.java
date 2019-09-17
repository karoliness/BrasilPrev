package com.desafio.brasilPrev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.desafio.brasilPrev.dto.CategoriaDto;
import com.desafio.brasilPrev.dto.ProdutoDto;
import com.desafio.brasilPrev.modelo.Categoria;
import com.desafio.brasilPrev.modelo.Produto;
import com.desafio.brasilPrev.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    private Categoria mapeiaCategoria(CategoriaDto categoriaDto){
        Categoria categoria = new Categoria(categoriaDto.nome);
        return categoria;
    }

    private CategoriaDto mapearCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.id = categoria.getId();
        categoriaDto.nome = categoria.getNome();
        return categoriaDto;
    }

    private ProdutoDto mapearProdutoDto(Produto produto) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.id = produto.getId();
        produtoDto.nome = produto.getNome();
        produtoDto.descricao = produto.getDescricao();
        produtoDto.preco = produto.getPreco();
        produtoDto.quantidade = produto.getQuantidade();
        produtoDto.categoria = mapearCategoriaDto(produto.getCategoria());
        produtoDto.foto = produto.getFoto();
    
        return produtoDto;
      }

    public List<ProdutoDto> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(this::mapearProdutoDto).collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody ProdutoDto produtoDto) {
        Categoria categoria = mapeiaCategoria(produtoDto.categoria);
        Produto produto = new Produto(categoria, produtoDto.nome, produtoDto.preco, 
        produtoDto.quantidade, produtoDto.descricao, produtoDto.foto);
        produtoRepository.save(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body("produto criado com sucesso");
    }

    @PutMapping("/editar")
    public ResponseEntity editar(@RequestBody ProdutoDto produtoDto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoDto.id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.AlterarCategoria(produtoDto.categoria);
            produto.AlterarNome(produtoDto.nome);
            produto.AlterarPreco(produtoDto.preco);
            produto.AlterarQuantidade(produtoDto.quantidade);
            produto.AlterarDescricao(produtoDto.descricao);
            produto.AlterarFoto(produtoDto.foto);
            produtoRepository.save(produto);

            return ResponseEntity.ok().body("produto alterado com sucesso");
        }
        return ResponseEntity.badRequest().body("Erro ao alterar o produto");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().body("produto excluído com sucesso");
        }
        return ResponseEntity.badRequest().body("Erro ao excluir o produto");
    }
}