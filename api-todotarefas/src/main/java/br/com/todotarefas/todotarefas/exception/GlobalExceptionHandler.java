package br.com.todotarefas.todotarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<ResponseError> handleTarefaNaoEncontradaException(TarefaNaoEncontradaException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(TarefaInvalidaException.class)
    public ResponseEntity<ResponseError> handleTarefaInvalidaException(TarefaInvalidaException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(exception = {AtualizaTituloTarefaExistenteException.class, TituloTarefaExistenteException.class})
    public ResponseEntity<ResponseError> handleTarefasException(TarefasException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}