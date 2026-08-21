package com.vestimentaseden.vestimentas_eden.controller;

import com.vestimentaseden.vestimentas_eden.facade.PedidoFacade;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoFacade pedidoFacade;

    @PostMapping("/pedido")
    public void criarPedido (@RequestBody @Valid PedidoRequest pedidoRequest){

        System.out.println(pedidoRequest);

        this.pedidoFacade.criarPedido(pedidoRequest);
    }

}
