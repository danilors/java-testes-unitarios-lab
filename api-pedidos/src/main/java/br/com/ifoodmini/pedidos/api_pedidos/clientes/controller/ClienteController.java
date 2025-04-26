package br.com.ifoodmini.pedidos.api_pedidos.clientes.controller;

import br.com.ifoodmini.pedidos.api_pedidos.clientes.entity.ClienteEntity;
import br.com.ifoodmini.pedidos.api_pedidos.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteEntity> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteEntity createCliente(@RequestBody ClienteEntity cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        return clienteService.findById(id)
                .map(existingCliente -> {
                    cliente.setId(existingCliente.getId());
                    return ResponseEntity.ok(clienteService.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clienteService.findById(id).isPresent()) {
            clienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}