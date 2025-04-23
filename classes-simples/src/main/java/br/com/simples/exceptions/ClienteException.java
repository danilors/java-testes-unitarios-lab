package br.com.simples.exceptions;

public class ClienteException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteException(Throwable cause) {
        super(cause);
    }
}
