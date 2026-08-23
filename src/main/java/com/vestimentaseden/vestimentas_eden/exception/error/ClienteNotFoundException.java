package com.vestimentaseden.vestimentas_eden.exception.error;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(String id) {
        super("cliente não encontrado. clienteId:" + id);
    }
}
