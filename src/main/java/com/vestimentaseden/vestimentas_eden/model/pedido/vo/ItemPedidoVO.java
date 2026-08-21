package com.vestimentaseden.vestimentas_eden.model.pedido.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoVO {

    private String id;
    private String produtoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;
}
