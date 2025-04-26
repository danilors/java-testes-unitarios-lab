package br.com.ifoodmini.pedidos.api_pedidos.clientes.repository;

import br.com.ifoodmini.pedidos.api_pedidos.clientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}