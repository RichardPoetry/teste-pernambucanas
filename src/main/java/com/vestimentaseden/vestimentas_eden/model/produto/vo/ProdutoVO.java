package com.vestimentaseden.vestimentas_eden.model.produto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVO {

    private String id;
    private String nome;
    private BigDecimal preco;
    private Integer estoque;
}
