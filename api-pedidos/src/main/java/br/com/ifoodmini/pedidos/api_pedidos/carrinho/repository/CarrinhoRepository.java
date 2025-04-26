package br.com.ifoodmini.pedidos.api_pedidos.carrinho.repository;

import br.com.ifoodmini.pedidos.api_pedidos.carrinho.entity.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {
}