package br.com.produto.api_produtos.dto;

import br.com.produto.api_produtos.entity.ProdutoEntity;
import br.com.produto.api_produtos.enums.Categoria;
import br.com.produto.api_produtos.enums.StatusProduto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder // Lombok annotation to provide the builder pattern
@AllArgsConstructor // Lombok annotation for constructor with all args (used by builder)
@NoArgsConstructor // Lombok annotation for no-args constructor
public class ProdutoDTO {

    // --- Existing Fields ---
    private String descricao;
    private Double preco;
    private Categoria categoria;

    // --- Added Fields from ProdutoEntity ---
    private Integer quantidadeEstoque;
    private String sku;
    private String marca;
    private String imageUrl;
    private StatusProduto status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtualizacao;

    /**
     * Creates a ProdutoDTO instance from a ProdutoEntity instance.
     * Includes all relevant fields from the entity.
     *
     * @param produtoEntity The entity to map from.
     * @return A new ProdutoDTO instance populated with data from the entity,
     * or null if the input entity is null.
     */
    public static ProdutoDTO from(ProdutoEntity produtoEntity) {
        // Optional: Handle null input gracefully
        if (produtoEntity == null) {
            return null;
        }

        // Use the builder pattern provided by Lombok's @Builder
        return ProdutoDTO.builder()
                // Map existing fields
                .descricao(produtoEntity.getDescricao())
                .preco(produtoEntity.getPreco())
                .categoria(produtoEntity.getCategoria())
                // Map added fields
                .quantidadeEstoque(produtoEntity.getQuantidadeEstoque())
                .sku(produtoEntity.getSku())
                .marca(produtoEntity.getMarca())
                .imageUrl(produtoEntity.getImageUrl())
                .status(produtoEntity.getStatus())
                .dataCriacao(produtoEntity.getDataCriacao())
                .dataAtualizacao(produtoEntity.getDataAtualizacao())
                .build(); // Build the DTO instance
    }

    /**
     * Converts this ProdutoDTO back into a ProdutoEntity.
     * Useful for creating or updating entities based on DTO data.
     * Note: ID and Timestamps (dataCriacao, dataAtualizacao) are generally
     * handled by JPA/Hibernate and should not be set directly here.
     *
     * @return A new ProdutoEntity instance populated with data from this DTO.
     */
    public ProdutoEntity toEntity() {
        ProdutoEntity entity = new ProdutoEntity();
        // Map basic fields from DTO to Entity
        entity.setDescricao(this.descricao);
        entity.setPreco(this.preco);
        entity.setCategoria(this.categoria);

        // Map added fields from DTO to Entity
        entity.setQuantidadeEstoque(this.quantidadeEstoque); // Use value from DTO
        entity.setSku(this.sku);                             // Use value from DTO
        entity.setMarca(this.marca);                         // Use value from DTO
        entity.setImageUrl(this.imageUrl);                   // Use value from DTO
        entity.setStatus(this.status);                       // Use value from DTO

        // DO NOT set ID here - it's either generated or should be set separately when updating.
        // DO NOT set dataCriacao or dataAtualizacao here - they are managed by
        // @CreationTimestamp and @UpdateTimestamp annotations in the entity.
        entity.setDataAtualizacao(LocalDate.now());
        entity.setDataCriacao(LocalDate.now());

        return entity;
    }
}