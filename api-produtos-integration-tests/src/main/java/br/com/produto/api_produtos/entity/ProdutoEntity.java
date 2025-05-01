package br.com.produto.api_produtos.entity;

import br.com.produto.api_produtos.enums.Categoria;
import br.com.produto.api_produtos.enums.StatusProduto; // Assuming you create this enum
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate; 
@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    // --- Added Fields ---

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque = 0; // Default to 0

    @Column(name = "sku", unique = true, length = 100)
    private String sku;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusProduto status = StatusProduto.ATIVO; // Default status

    @CreationTimestamp // Automatically set on creation
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @UpdateTimestamp // Automatically set on update
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao;

    // --- End of Added Fields ---


    // Optional: Constructor without ID (useful for creating new entities before saving)
    public ProdutoEntity(String descricao, Double preco, Categoria categoria) {
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        // Consider initializing other required fields here too
        this.quantidadeEstoque = 0;
        this.status = StatusProduto.ATIVO;
    }

    // Lombok generates getters/setters/no-args constructor
}