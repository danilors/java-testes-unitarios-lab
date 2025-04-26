package br.com.ifoodmini.pedidos.api_pedidos.clientes.entity;

import br.com.ifoodmini.pedidos.api_pedidos.enderecos.entity.EnderecoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.carrinho.entity.CarrinhoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.pedidos.entity.PedidoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<EnderecoEntity> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private CarrinhoEntity carrinho;

    // Getters e Setters
}