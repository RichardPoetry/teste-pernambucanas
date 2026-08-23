package com.vestimentaseden.vestimentas_eden.exception.error;

import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;

public class PedidoNaocanceladoException extends RuntimeException {
    public PedidoNaocanceladoException(StatusPedidoEnum status) {
        super("pedido não pode ser cancelado pelo status em que se encontra. STATUS: "+ status.toString());
    }
}
