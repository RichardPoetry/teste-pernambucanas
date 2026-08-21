package com.vestimentaseden.vestimentas_eden.model.pedido.response;


import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = 6874181196885056772L;

    private String id;
    private String nomeCliente;
    private List<ItemPedidoResponse> itens = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal desconto;
    private BigDecimal frete;
    private BigDecimal total;
    private Integer pontosGerados;
    private CupomEnum cupom;
    private StatusPedidoEnum status;
}
