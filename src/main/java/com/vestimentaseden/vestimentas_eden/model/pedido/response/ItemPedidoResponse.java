package com.vestimentaseden.vestimentas_eden.model.pedido.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = 2976432697521656680L;


    private String id;
    private String produtoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;
}
