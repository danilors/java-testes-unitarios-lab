package br.com.ifoodmini.pedidos.api_pedidos.enderecos.controller;

import br.com.ifoodmini.pedidos.api_pedidos.enderecos.entity.EnderecoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.enderecos.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoEntity> getAllEnderecos() {
        return enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoEntity> getEnderecoById(@PathVariable Long id) {
        return enderecoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnderecoEntity createEndereco(@RequestBody EnderecoEntity endereco) {
        return enderecoService.save(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoEntity> updateEndereco(@PathVariable Long id, @RequestBody EnderecoEntity endereco) {
        return enderecoService.findById(id)
                .map(existingEndereco -> {
                    endereco.setId(existingEndereco.getId());
                    return ResponseEntity.ok(enderecoService.save(endereco));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        if (enderecoService.findById(id).isPresent()) {
            enderecoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}