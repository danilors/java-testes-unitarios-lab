package br.com.produto.api_produtos.enums;

import lombok.Getter;

/**
 * Enum representing the possible statuses of a product listing in the marketplace.
 */
@Getter
public enum StatusProduto {

    ATIVO("Ativo"),                     // Product is active and available for sale.
    INATIVO("Inativo"),                 // Product is inactive, hidden from users but exists in the system.
    ESGOTADO("Esgotado"),               // Product is temporarily out of stock.
    RASCUNHO("Rascunho"),               // Product information is saved but not yet published/activated.
    DESCONTINUADO("Descontinuado");     // Product is no longer sold or available.

    /**
     * -- GETTER --
     *  Gets the user-friendly display name for the status.
     *
     * @return The friendly name (e.g., "Ativo", "Inativo").
     */
    private final String nomeAmigavel;

    StatusProduto(String nomeAmigavel) {
        this.nomeAmigavel = nomeAmigavel;
    }

    // Optional: Method to find enum by friendly name (similar to Categoria)
    public static StatusProduto fromNomeAmigavel(String nome) {
        for (StatusProduto status : StatusProduto.values()) {
            if (status.nomeAmigavel.equalsIgnoreCase(nome)) {
                return status;
            }
        }
        // Handle case where name doesn't match - return a default or throw exception
        // throw new IllegalArgumentException("Nenhum status encontrado para o nome: " + nome);
        return null; // Or return a default like INATIVO
    }
}