package br.com.ifoodmini.pedidos.api_pedidos.produtos.service;

import br.com.ifoodmini.pedidos.api_pedidos.produtos.entity.ProdutoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoEntity save(ProdutoEntity produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}