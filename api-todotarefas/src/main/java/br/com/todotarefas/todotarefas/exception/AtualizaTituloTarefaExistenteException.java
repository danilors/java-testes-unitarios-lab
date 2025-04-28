package br.com.todotarefas.todotarefas.exception;

public class AtualizaTituloTarefaExistenteException extends TarefasException {
    public AtualizaTituloTarefaExistenteException(String titulo) {
        super("A Tarefa nao pode ser atualizada. Já existe uma tarefa com o título: " + titulo);
    }
}
