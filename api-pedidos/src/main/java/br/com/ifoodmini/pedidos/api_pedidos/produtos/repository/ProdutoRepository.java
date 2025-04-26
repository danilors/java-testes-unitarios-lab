package br.com.ifoodmini.pedidos.api_pedidos.produtos.repository;

import br.com.ifoodmini.pedidos.api_pedidos.produtos.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}