package br.com.todotarefas.todotarefas.integration;

import br.com.todotarefas.todotarefas.dto.TarefaDTO;
import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import br.com.todotarefas.todotarefas.repository.TarefaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")

@DisplayName("Testes de Integração para Tarefas")
public class TarefaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve listar todas as tarefas")
    void deveListarTodasAsTarefas() throws Exception {
        tarefaRepository.save(new TarefaEntity(null, "Tarefa 1", "Descrição 1", false));
        tarefaRepository.save(new TarefaEntity(null, "Tarefa 2", "Descrição 2", true));

        mockMvc.perform(get("/api/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Deve buscar tarefa por ID")
    void deveBuscarTarefaPorId() throws Exception {
        TarefaEntity tarefa = tarefaRepository.save(new TarefaEntity(null, "Tarefa 1", "Descrição 1", false));

        mockMvc.perform(get("/api/tarefas/{id}", tarefa.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Tarefa 1"));
    }

    @Test
    @DisplayName("Deve retornar 404 ao buscar tarefa inexistente")
    void deveRetornar404AoBuscarTarefaInexistente() throws Exception {
        mockMvc.perform(get("/api/tarefas/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve criar nova tarefa")
    void deveCriarNovaTarefa() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO("Nova Tarefa", "Descrição da nova tarefa", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Nova Tarefa"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao criar tarefa com título inválido")
    void deveRetornar400AoCriarTarefaComTituloInvalido() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO("", "Descrição inválida", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve atualizar tarefa existente")
    void deveAtualizarTarefaExistente() throws Exception {
        TarefaEntity tarefa = tarefaRepository.save(new TarefaEntity(null, "Tarefa 1", "Descrição 1", false));
        TarefaDTO tarefaAtualizada = new TarefaDTO("Tarefa Atualizada", "Descrição atualizada", true);

        mockMvc.perform(put("/api/tarefas/{id}", tarefa.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Tarefa Atualizada"));
    }

    @Test
    @DisplayName("Deve retornar 404 ao atualizar tarefa inexistente")
    void deveRetornar404AoAtualizarTarefaInexistente() throws Exception {
        TarefaDTO tarefaAtualizada = new TarefaDTO("Tarefa Atualizada", "Descrição atualizada", true);

        mockMvc.perform(put("/api/tarefas/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve deletar tarefa existente")
    void deveDeletarTarefaExistente() throws Exception {
        TarefaEntity tarefa = tarefaRepository.save(new TarefaEntity(null, "Tarefa 1", "Descrição 1", false));

        mockMvc.perform(delete("/api/tarefas/{id}", tarefa.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar 404 ao deletar tarefa inexistente")
    void deveRetornar404AoDeletarTarefaInexistente() throws Exception {
        mockMvc.perform(delete("/api/tarefas/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar 400 ao criar tarefa com título existente")
    void deveRetornar400AoCriarTarefaComTituloExistente() throws Exception {
        tarefaRepository.save(new TarefaEntity(null, "Tarefa Duplicada", "Descrição", false));
        TarefaDTO tarefaDTO = new TarefaDTO("Tarefa Duplicada", "Outra descrição", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Já existe uma tarefa com o título: Tarefa Duplicada"));
    }

    @Test
    @DisplayName("Deve retornar 400 ao criar tarefa com descrição inválida")
    void deveRetornar400AoCriarTarefaComDescricaoInvalida() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO("Tarefa Válida", "", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("A descrição da tarefa não pode ser nula ou vazia."));
    }

    @Test
    @DisplayName("Deve retornar 400 ao criar tarefa com título muito longo")
    void deveRetornar400AoCriarTarefaComTituloMuitoLongo() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO("Título muito longo para ser aceito", "Descrição válida", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("O título da tarefa não pode ter mais de 20 caracteres."));
    }

    @Test
    @DisplayName("Deve retornar 400 ao criar tarefa com descrição muito longa")
    void deveRetornar400AoCriarTarefaComDescricaoMuitoLonga() throws Exception {
        TarefaDTO tarefaDTO = new TarefaDTO("Título válido",
                "Descrição muuuuuuuuuuuuito longa para ser aceita pelo sistema", false);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("A descrição da tarefa não pode ter mais de 60 caracteres."));
    }

    @Test
    @DisplayName("Deve retornar 400 ao tentar atualizar tarefa com título já existente")
    void deveRetornar400AoAtualizarTarefaComTituloExistente() throws Exception {
        // Arrange: Create two tasks with different titles
        tarefaRepository.save(new TarefaEntity(null, "Tarefa 1", "Descrição 1", false));
        TarefaEntity tarefa = tarefaRepository.save(new TarefaEntity(null, "Tarefa 2", "Descrição 2", false));

        // Act: Attempt to update the second task with the title of the first task
        TarefaDTO tarefaAtualizada = new TarefaDTO("Tarefa 1", "Descrição atualizada", true);

        // Assert: Expect a 400 Bad Request with the appropriate error message
        mockMvc.perform(put("/api/tarefas/{id}", tarefa.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("A Tarefa nao pode ser atualizada. Já existe uma tarefa com o título: Tarefa 1"));
    }
}
