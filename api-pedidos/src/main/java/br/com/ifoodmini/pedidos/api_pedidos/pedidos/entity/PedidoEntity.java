package br.com.ifoodmini.pedidos.api_pedidos.pedidos.entity;

import br.com.ifoodmini.pedidos.api_pedidos.clientes.entity.ClienteEntity;
import br.com.ifoodmini.pedidos.api_pedidos.produtos.entity.ProdutoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToMany(mappedBy = "pedidos")
    private List<ProdutoEntity> produtos;

    // Getters and Setters
}