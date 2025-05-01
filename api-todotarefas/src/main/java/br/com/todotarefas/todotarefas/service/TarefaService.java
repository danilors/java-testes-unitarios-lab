package br.com.todotarefas.todotarefas.service;

import br.com.todotarefas.todotarefas.dto.TarefaDTO;
import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import br.com.todotarefas.todotarefas.exception.TarefaNaoEncontradaException;
import br.com.todotarefas.todotarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaEntity> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<TarefaEntity> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public TarefaEntity salvar(TarefaDTO tarefaDTO) {

        TarefaEntity tarefa = new TarefaEntity();
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setConcluida(tarefaDTO.concluida());
        return tarefaRepository.save(tarefa);
    }

    public TarefaEntity atualizar(Long id, TarefaDTO tarefaDTO) {
        TarefaEntity tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));

        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setConcluida(tarefaDTO.concluida());
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }
}