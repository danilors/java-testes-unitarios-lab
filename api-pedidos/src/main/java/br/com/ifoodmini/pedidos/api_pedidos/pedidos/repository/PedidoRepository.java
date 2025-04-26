package br.com.ifoodmini.pedidos.api_pedidos.pedidos.repository;

import br.com.ifoodmini.pedidos.api_pedidos.pedidos.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}