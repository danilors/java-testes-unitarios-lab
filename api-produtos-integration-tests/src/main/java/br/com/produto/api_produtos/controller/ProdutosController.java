package br.com.produto.api_produtos.controller;

import br.com.produto.api_produtos.dto.ProdutoDTO;
import br.com.produto.api_produtos.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

    private final ProdutoService produtoService;

    public ProdutosController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProdutos() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.salvar(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
