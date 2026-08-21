package com.vestimentaseden.vestimentas_eden.model.pedido.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRequest {

    @NotNull(message ="voce precisa informar o produtoId")
    private String produtoId;

    @Positive(message = "A quantidade de produto deve ser maior que zero")
    private Integer quantidade;

}
