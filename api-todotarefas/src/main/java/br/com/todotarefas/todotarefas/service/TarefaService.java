package br.com.todotarefas.todotarefas.service;

import br.com.todotarefas.todotarefas.dto.TarefaDTO;
import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import br.com.todotarefas.todotarefas.exception.AtualizaTituloTarefaExistenteException;
import br.com.todotarefas.todotarefas.exception.TarefaInvalidaException;
import br.com.todotarefas.todotarefas.exception.TarefaNaoEncontradaException;
import br.com.todotarefas.todotarefas.exception.TituloTarefaExistenteException;
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
        if (tarefaRepository.findByTitulo(tarefaDTO.titulo()).isPresent()) {
            throw new TituloTarefaExistenteException(tarefaDTO.titulo());
        }
        if (tarefaDTO.titulo() == null || tarefaDTO.titulo().isEmpty()) {
            throw new TarefaInvalidaException("O título da tarefa não pode ser nulo ou vazio.");
        }
        if (tarefaDTO.descricao() == null || tarefaDTO.descricao().isEmpty()) {
            throw new TarefaInvalidaException("A descrição da tarefa não pode ser nula ou vazia.");
        }

        if (tarefaDTO.titulo().length() > 20) {
            throw new TarefaInvalidaException("O título da tarefa não pode ter mais de 20 caracteres.");
        }

        if (tarefaDTO.descricao().length() > 60) {
            throw new TarefaInvalidaException("A descrição da tarefa não pode ter mais de 60 caracteres.");
        }
        TarefaEntity tarefa = new TarefaEntity();
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setConcluida(tarefaDTO.concluida());
        return tarefaRepository.save(tarefa);
    }

    public TarefaEntity atualizar(Long id, TarefaDTO tarefaDTO) {
        TarefaEntity tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
        tarefaRepository.findByTitulo(tarefaDTO.titulo()).ifPresent(t -> {
            if (!t.getId().equals(id)) {
                throw new AtualizaTituloTarefaExistenteException(tarefaDTO.titulo());
            }
        });

        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setConcluida(tarefaDTO.concluida());
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNaoEncontradaException(id);
        }
        tarefaRepository.deleteById(id);
    }
}