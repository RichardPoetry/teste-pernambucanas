package com.vestimentaseden.vestimentas_eden.exception.error;

public class PedidoNotFoundException extends RuntimeException {

  public PedidoNotFoundException() {
    super("Pedido não encontrado.");
  }

  public PedidoNotFoundException(String id) {
    super("Pedido não encontrado. ID: " + id);
  }
}
