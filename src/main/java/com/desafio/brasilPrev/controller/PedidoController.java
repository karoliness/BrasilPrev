package com.desafio.brasilPrev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.desafio.brasilPrev.dto.PedidoDto;
import com.desafio.brasilPrev.dto.PedidoItemDto;
import com.desafio.brasilPrev.modelo.Cliente;
import com.desafio.brasilPrev.modelo.Pedido;
import com.desafio.brasilPrev.modelo.PedidoItem;
import com.desafio.brasilPrev.modelo.Produto;
import com.desafio.brasilPrev.repository.ClienteRepository;
import com.desafio.brasilPrev.repository.PedidoRepository;
import com.desafio.brasilPrev.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity criar(@RequestBody PedidoDto pedidoDto) throws Exception {
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(pedidoDto.cliente_id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            List<PedidoItem> pedidoItens = pedidoDto.pedidoItens.stream().map(this::mapearPedidoItem)
                    .collect(Collectors.toList());
            Pedido pedido = new Pedido(cliente, pedidoItens, pedidoDto.sessao);

            this.pedidoRepository.save(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido Salvo com sucesso");
        }
        return ResponseEntity.badRequest().body("Cliente não existe");
    }

    private PedidoItem mapearPedidoItem(PedidoItemDto pedidoItemDto) {
        Optional<Produto> produtoOptional = this.produtoRepository.findById(pedidoItemDto.produto_id);
        Produto produto = produtoOptional.get();
        PedidoItem pedidoItem = new PedidoItem(produto, pedidoItemDto.quantidade,
                pedidoItemDto.valorUnitario);

        return pedidoItem;
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok().body("pedido excluído com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao excluir pedido");
    }
}
