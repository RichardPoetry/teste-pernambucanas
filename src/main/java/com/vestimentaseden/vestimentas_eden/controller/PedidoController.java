package com.vestimentaseden.vestimentas_eden.controller;

import com.vestimentaseden.vestimentas_eden.facade.PedidoFacade;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.PedidoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public PedidoResponse criarPedido (@RequestBody @Valid PedidoRequest pedidoRequest){

        System.out.println(pedidoRequest);

        return this.pedidoFacade.criarPedido(pedidoRequest);
    }

    @GetMapping("/pedidos/{id}")
    public PedidoResponse consultarPedido (@PathVariable String id){

        return pedidoFacade.consultarPedido(id);
    }

    @PostMapping("/pedido/{id}/pagamento")
    public void pagarPedido (@PathVariable String id){

         this.pedidoFacade.pagarPedido(id);
    }

    @PostMapping("/pedido/{id}/pagamento")
    public void cancelarPedido (@PathVariable String id){

        this.pedidoFacade.cancelarPedido(id);
    }
}
