package br.com.produto.api_produtos.service.impl;

import br.com.produto.api_produtos.dto.ProdutoDTO;
import br.com.produto.api_produtos.enums.Categoria;
import br.com.produto.api_produtos.repository.ProdutoRepository;
import br.com.produto.api_produtos.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoDTO::from)
                .toList();
    }

    @Override
    public void salvar(ProdutoDTO produtoDTO) {
        produtoRepository.save(produtoDTO.toEntity());
    }
}
