package br.com.ifoodmini.pedidos.api_pedidos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;

    @ManyToMany
    @JoinTable(
        name = "pedido_produto",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "pedido_id")
    )
    private List<PedidoEntity> pedidos;

    @ManyToMany(mappedBy = "produtos")
    private List<Carrinho> carrinhos;

    // Getters and Setters
}