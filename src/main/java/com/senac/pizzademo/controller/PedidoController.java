package com.senac.pizzademo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senac.pizzademo.model.Pedido;
import com.senac.pizzademo.model.Usuario;
import com.senac.pizzademo.repository.PedidoRepository;
import com.senac.pizzademo.repository.UsuarioRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("em produção");
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        return pedidoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(u -> ResponseEntity.ok(u.getPedidos()))
                .orElse(ResponseEntity.notFound().build());
    }
}
