package br.com.produto.api_produtos.service;

import br.com.produto.api_produtos.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    List<ProdutoDTO> listar();

    void salvar(ProdutoDTO produtoDTO);
}
