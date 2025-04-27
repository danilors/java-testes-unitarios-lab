package br.com.produto.api_produtos.integration;

import br.com.produto.api_produtos.ApiProdutosApplication;
import br.com.produto.api_produtos.config.DefaultConfig;
import br.com.produto.api_produtos.dto.ProdutoDTO;
import br.com.produto.api_produtos.enums.Categoria;
import br.com.produto.api_produtos.enums.StatusProduto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {ApiProdutosApplication.class, DefaultConfig.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProdutosIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInsertAndRetrieveProduto() throws Exception {
        // Step 1: Create a new ProdutoDTO
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .descricao("Produto Teste")
                .preco(99.99)
                .categoria(Categoria.ELETRONICOS)
                .quantidadeEstoque(10)
                .sku("TEST123")
                .marca("Marca Teste")
                .imageUrl("http://example.com/image.jpg")
                .status(StatusProduto.ATIVO)
                .build();

        // Step 2: Perform POST request to save the product
        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isCreated());

        // Step 3: Perform GET request to retrieve all products
        mockMvc.perform(get("/api/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Produto Teste"))
                .andExpect(jsonPath("$[0].preco").value(99.99))
                .andExpect(jsonPath("$[0].categoria").value("ELETRONICOS"));
    }
}