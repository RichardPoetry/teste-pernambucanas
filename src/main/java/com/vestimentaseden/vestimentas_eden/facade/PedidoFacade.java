package com.vestimentaseden.vestimentas_eden.facade;

import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.model.mapper.ResponseMapper;
import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.PedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.PedidoVO;
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

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest) {

        List<ProdutoVO> produtoVOS = produtoService.verificarItemEstoque(pedidoRequest.getItens());
        System.out.println("todos itens contém no estoque");

        ClienteVO cliente = clienteService.buscarCliente(pedidoRequest.getClientId());

        PedidoVO pedidoVO = this.pedidoService.criarPedido(pedidoRequest, cliente, produtoVOS);

        this.produtoService.atualizaEstoquePedidoCriado(pedidoRequest.getItens());

        return  ResponseMapper.INSTANCE.toResponse(pedidoVO);
    }

    public PedidoResponse consultarPedido(String id) {

        PedidoVO pedidoVO = pedidoService.consultarPedido(id);

        return ResponseMapper.INSTANCE.toResponse(pedidoVO);
    }

    public void pagarPedido(String id) {
        this.pedidoService.pagarPedido(id);
    }

    public void cancelarPedido(String id) {

        PedidoVO pedidoVO = pedidoService.consultarPedido(id);

        if(pedidoVO.getStatus() == StatusPedidoEnum.ENVIADO ||
           pedidoVO.getStatus() == StatusPedidoEnum.ENTREGUE) {
            System.out.println("pedido não pode ser cancelado");
            return;
        }

        this.produtoService.atualizaEstoquePedidoCancelado(pedidoVO.getItens());

        this.pedidoService.AtualizarStatusPedidoCancelado(id);

    }
}


