package com.vestimentaseden.vestimentas_eden.service;

import com.vestimentaseden.vestimentas_eden.model.cliente.TipoClienteEnum;
import com.vestimentaseden.vestimentas_eden.model.cliente.mapper.ClienteMapper;
import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.mapper.PedidoMapper;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.ItemRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.PedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ItemPedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.vestimentaseden.vestimentas_eden.ApplicationConstants.DESCONTO_CLIENTE_PLUS;
import static com.vestimentaseden.vestimentas_eden.ApplicationConstants.DESCONTO_CUPOM_DESC10;
import static com.vestimentaseden.vestimentas_eden.ApplicationConstants.FRETE;
import static com.vestimentaseden.vestimentas_eden.ApplicationConstants.VALOR_MINIMO_FRETE_GRATIS;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest, ClienteVO cliente,  List<ProdutoVO> produtoVO) {

        BigDecimal subtotal = calculoSubTotal(pedidoRequest.getItens(),produtoVO);

        BigDecimal frete = calculoFrete(subtotal,pedidoRequest.getCupom());

        BigDecimal desconto= calculoDesconto(subtotal, cliente, pedidoRequest.getCupom());

        BigDecimal total = calcularTotal(subtotal,desconto,frete);

        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.toClienteEntity(cliente);

        List<ItemPedidoEntity> itemPedidoEntities = PedidoMapper.INSTANCE.toItemPedidoEntities(produtoVO,pedidoRequest.getItens());

        PedidoEntity pedido = PedidoMapper.INSTANCE.toPedidoEntity(pedidoRequest,clienteEntity,itemPedidoEntities,subtotal,frete,desconto,total);

        itemPedidoEntities.forEach(item -> item.setPedido(pedido));

        pedidoRepository.save(pedido);

       return PedidoMapper.INSTANCE.toPedidoResponse(pedido);
    }

    public PedidoResponse consultarPedido(String id) {

        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return PedidoMapper.INSTANCE.toPedidoResponse(pedido);
    }


    private BigDecimal calculoSubTotal(List<ItemRequest> itens, List<ProdutoVO> produtos) {

        BigDecimal subTotal = BigDecimal.ZERO;

        for (ItemRequest item : itens) {

            ProdutoVO produto = produtos.stream()
                    .filter(p -> p.getId().equals(item.getProdutoId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado: " + item.getProdutoId()));

            BigDecimal valorItem = produto.getPreco()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            subTotal = subTotal.add(valorItem);
        }

        return subTotal;
    }

    private BigDecimal calculoFrete(BigDecimal subTotal, CupomEnum cupom) {

        if (subTotal.compareTo(VALOR_MINIMO_FRETE_GRATIS) >= 0
                || CupomEnum.FRETEGRATIS.equals(cupom)) {
            return BigDecimal.ZERO;
        }

        return FRETE;
    }

    private BigDecimal calculoDesconto(BigDecimal subTotal, ClienteVO cliente, CupomEnum cupom) {

        BigDecimal percentual = BigDecimal.ZERO;

        if (cliente.getTipo() == TipoClienteEnum.PLUS) {
            percentual = percentual.add(DESCONTO_CLIENTE_PLUS);
        }

        if (cupom == CupomEnum.DESC10) {
            percentual = percentual.add(DESCONTO_CUPOM_DESC10);
        }

        return subTotal.multiply(percentual)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularTotal(BigDecimal subTotal, BigDecimal desconto, BigDecimal frete) {

        BigDecimal total = subTotal
                .subtract(desconto)
                .add(frete);

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO.setScale(2);
//            gerar excessão
        }

        return total;
    }


}
