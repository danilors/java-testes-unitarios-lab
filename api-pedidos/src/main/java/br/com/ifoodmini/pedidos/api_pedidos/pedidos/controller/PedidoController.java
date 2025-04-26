package br.com.ifoodmini.pedidos.api_pedidos.pedidos.controller;

import br.com.ifoodmini.pedidos.api_pedidos.pedidos.entity.PedidoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoEntity> getAllPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> getPedidoById(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoEntity createPedido(@RequestBody PedidoEntity pedido) {
        return pedidoService.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoEntity> updatePedido(@PathVariable Long id, @RequestBody PedidoEntity pedido) {
        return pedidoService.findById(id)
                .map(existingPedido -> {
                    pedido.setId(existingPedido.getId());
                    return ResponseEntity.ok(pedidoService.save(pedido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (pedidoService.findById(id).isPresent()) {
            pedidoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}