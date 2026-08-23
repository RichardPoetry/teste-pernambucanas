package com.vestimentaseden.vestimentas_eden.exception.error;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String id) {
        super("produto não encontrado. produtoId:" + id);
    }
}
