package br.com.ifoodmini.pedidos.api_pedidos.carrinho.controller;

import br.com.ifoodmini.pedidos.api_pedidos.carrinho.entity.CarrinhoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoEntity> getCarrinhoById(@PathVariable Long id) {
        return carrinhoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarrinhoEntity createCarrinho(@RequestBody CarrinhoEntity carrinho) {
        return carrinhoService.save(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoEntity> updateCarrinho(@PathVariable Long id, @RequestBody CarrinhoEntity carrinho) {
        return carrinhoService.findById(id)
                .map(existingCarrinho -> {
                    carrinho.setId(existingCarrinho.getId());
                    return ResponseEntity.ok(carrinhoService.save(carrinho));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long id) {
        if (carrinhoService.findById(id).isPresent()) {
            carrinhoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}