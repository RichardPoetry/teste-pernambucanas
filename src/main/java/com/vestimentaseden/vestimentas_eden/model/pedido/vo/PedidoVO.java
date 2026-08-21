package com.vestimentaseden.vestimentas_eden.model.pedido.vo;

import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoVO {

    private String id;
    private String nomeCliente;
    private List<ItemPedidoVO> itens;
    private BigDecimal subtotal;
    private BigDecimal desconto;
    private BigDecimal frete;
    private BigDecimal total;
    private Integer pontosGerados;
    private CupomEnum cupom;
    private StatusPedidoEnum status;
}
