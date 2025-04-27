package br.com.todotarefas.todotarefas.repository;

import br.com.todotarefas.todotarefas.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
}