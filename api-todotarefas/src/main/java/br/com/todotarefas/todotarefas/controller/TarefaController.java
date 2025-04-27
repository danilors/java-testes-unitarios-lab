package br.com.todotarefas.todotarefas.controller;

import br.com.todotarefas.todotarefas.dto.TarefaDTO;
import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import br.com.todotarefas.todotarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaEntity> listarTodas() {
        return tarefaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaEntity> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarefaEntity criar(@RequestBody TarefaDTO tarefaDTO) {
        return tarefaService.salvar(tarefaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaEntity> atualizar(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.atualizar(id, tarefaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}