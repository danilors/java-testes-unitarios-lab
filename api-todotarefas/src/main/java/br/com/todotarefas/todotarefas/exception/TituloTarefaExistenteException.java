package br.com.todotarefas.todotarefas.exception;

public class TituloTarefaExistenteException extends TarefasException {
    public TituloTarefaExistenteException(String titulo) {
        super("Já existe uma tarefa com o título: " + titulo);
    }
}
