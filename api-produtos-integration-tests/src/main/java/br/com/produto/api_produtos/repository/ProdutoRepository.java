package br.com.produto.api_produtos.repository;

import br.com.produto.api_produtos.entity.ProdutoEntity;
import br.com.produto.api_produtos.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indicates that this is a Spring Data repository component
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    // Spring Data JPA will automatically provide implementations for standard CRUD operations
    // (save, findById, findAll, deleteById, etc.) based on the JpaRepository interface.

    // You can add custom query methods here if needed.
    // For example, to find products by description:
    List<ProdutoEntity> findByDescricaoContainingIgnoreCase(String descricao);

    // Or find products by category:
     List<ProdutoEntity> findByCategoria(Categoria categoria);
}