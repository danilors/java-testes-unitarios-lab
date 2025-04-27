package br.com.produto.api_produtos.enums;

import lombok.Getter;

@Getter
public enum Categoria {

    ELETRONICOS("Eletrônicos"),
    ROUPAS("Roupas"),
    CALCADOS("Calçados"),
    CASA_E_JARDIM("Casa e Jardim"),
    LIVROS("Livros"),
    BRINQUEDOS_E_JOGOS("Brinquedos e Jogos"),
    ESPORTES_E_LAZER("Esportes e Lazer"),
    BELEZA_E_CUIDADO_PESSOAL("Beleza e Cuidado Pessoal"),
    AUTOMOTIVO("Automotivo"),
    ALIMENTOS_E_BEBIDAS("Alimentos e Bebidas"),
    MOVEIS_E_DECORACAO("Móveis e Decoração"),
    FERRAMENTAS("Ferramentas"),
    SAUDE("Saúde"),
    PET_SHOP("Pet Shop"),
    INSTRUMENTOS_MUSICAIS("Instrumentos Musicais"),
    OUTROS("Outros"); // Categoria genérica para itens não classificados

    private final String nomeAmigavel;

    Categoria(String nomeAmigavel) {
        this.nomeAmigavel = nomeAmigavel;
    }

    // Opcional: Método para buscar enum pelo nome amigável (ignora case)
    public static Categoria fromNomeAmigavel(String nome) {
        for (Categoria cat : Categoria.values()) {
            if (cat.nomeAmigavel.equalsIgnoreCase(nome)) {
                return cat;
            }
        }
        // Retorna OUTROS ou lança exceção se não encontrar
        // throw new IllegalArgumentException("Nenhuma categoria encontrada para o nome: " + nome);
        return OUTROS;
    }
}