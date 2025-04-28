package br.com.todotarefas.todotarefas.exception;

public class TarefaNaoEncontradaException extends TarefasException {
    public TarefaNaoEncontradaException(Long id) {
        super("Tarefa não encontrada com o ID: " + id);
    }

}
