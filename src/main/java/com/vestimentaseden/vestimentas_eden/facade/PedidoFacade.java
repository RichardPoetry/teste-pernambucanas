package com.vestimentaseden.vestimentas_eden.facade;

import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.service.ClienteService;
import com.vestimentaseden.vestimentas_eden.service.PedidoService;
import com.vestimentaseden.vestimentas_eden.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoFacade {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    public void criarPedido(PedidoRequest pedidoRequest) {

        List<ProdutoVO> produtoVOS = produtoService.verificarItemEstoque(pedidoRequest.getItens());
        System.out.println("todos itens contém no estoque");

        ClienteVO cliente = clienteService.buscarCliente(pedidoRequest.getClientId());

        this.pedidoService.criarPedido(pedidoRequest, cliente, produtoVOS);

        this.produtoService.atualizaEstoque(pedidoRequest.getItens());
    }
}


