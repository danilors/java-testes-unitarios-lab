package br.com.todotarefas.todotarefas.exception;

public  class TarefasException extends RuntimeException {
    public TarefasException(String message) {
        super(message);
    }

    public TarefasException(String message, Throwable cause) {
        super(message, cause);
    }
}
