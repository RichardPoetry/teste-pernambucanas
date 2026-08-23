package com.vestimentaseden.vestimentas_eden.exception.error;

public class EstoqueInsuficienteException extends RuntimeException {

  public EstoqueInsuficienteException(String nomeProduto) {
    super("Estoque insuficiente para o produto: " + nomeProduto);
  }

  public EstoqueInsuficienteException(String nomeProduto, Integer estoqueDisponivel, Integer quantidadeSolicitada) {
    super(String.format(
            "Estoque insuficiente para o produto '%s'. Disponível: %d, Solicitado: %d",
            nomeProduto,
            estoqueDisponivel,
            quantidadeSolicitada
    ));
  }
}
