package com.vestimentaseden.vestimentas_eden.model.produto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 4858962975422140397L;

    private String nome;
    private String categoria;
    private double preco;
    private String estoque;



}
