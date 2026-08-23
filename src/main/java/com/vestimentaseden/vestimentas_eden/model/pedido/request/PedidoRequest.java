package com.vestimentaseden.vestimentas_eden.model.pedido.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1709865585034573114L;

    @NotBlank(message = "o campo clienteId não pode ser branco nem nulo")
    private String clientId;

    @NotEmpty(message = "É necessário informar pelo menos um item")
    @Valid
    private List<ItemRequest> itens;

    private CupomEnum cupom;
}