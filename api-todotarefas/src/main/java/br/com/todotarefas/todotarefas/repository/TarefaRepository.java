package br.com.todotarefas.todotarefas.repository;

import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    Optional<TarefaEntity> findByTitulo(String titulo);
}