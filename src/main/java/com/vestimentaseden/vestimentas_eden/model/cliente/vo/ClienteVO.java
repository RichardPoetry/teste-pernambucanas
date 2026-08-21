package com.vestimentaseden.vestimentas_eden.model.cliente.vo;

import com.vestimentaseden.vestimentas_eden.model.cliente.TipoClienteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteVO {

    private String id;

    private String nome;

    private TipoClienteEnum tipo;
}
