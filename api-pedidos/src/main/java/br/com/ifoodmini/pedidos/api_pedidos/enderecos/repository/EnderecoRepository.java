package br.com.ifoodmini.pedidos.api_pedidos.enderecos.repository;

import br.com.ifoodmini.pedidos.api_pedidos.enderecos.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}