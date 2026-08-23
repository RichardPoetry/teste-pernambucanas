package com.vestimentaseden.vestimentas_eden.exception;

import com.vestimentaseden.vestimentas_eden.exception.error.ClienteNotFoundException;
import com.vestimentaseden.vestimentas_eden.exception.error.EstoqueInsuficienteException;
import com.vestimentaseden.vestimentas_eden.exception.error.PedidoInvalidoException;
import com.vestimentaseden.vestimentas_eden.exception.error.PedidoNaocanceladoException;
import com.vestimentaseden.vestimentas_eden.exception.error.PedidoNotFoundException;
import com.vestimentaseden.vestimentas_eden.exception.error.ProdutoNotFoundException;
import com.vestimentaseden.vestimentas_eden.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProdutoNotFound(ProdutoNotFoundException ex,
                                                               HttpServletRequest request) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<ErrorResponse> handleEstoqueInsuficiente(
            EstoqueInsuficienteException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(PedidoNaocanceladoException.class)
    public ResponseEntity<ErrorResponse> handlePedidoNaoCancelado(
            PedidoNaocanceladoException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFound(
            ClienteNotFoundException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePedidoNotFound(
            PedidoNotFoundException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(PedidoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handlePedidoInvalido(
            PedidoInvalidoException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .toList();

        ErrorResponse body = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Erro de validação.")
                .path(request.getRequestURI())
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status,
                                                        String message,
                                                        String path) {
        ErrorResponse body =ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
        return ResponseEntity.status(status).body(body);
    }

}
