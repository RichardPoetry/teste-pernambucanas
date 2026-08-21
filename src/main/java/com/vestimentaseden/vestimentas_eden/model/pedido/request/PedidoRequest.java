package com.vestimentaseden.vestimentas_eden.model.pedido.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String clientId;

    @NotNull
    @Valid
    private List<ItemRequest> itens;

    private CupomEnum cupom;
}