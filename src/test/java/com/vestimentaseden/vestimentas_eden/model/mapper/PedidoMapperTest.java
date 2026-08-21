package com.vestimentaseden.vestimentas_eden.model.mapper;

import com.vestimentaseden.vestimentas_eden.dummies.Dummies;
import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.PedidoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ItemPedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMapperTest {


    @Test
    void deveConverterPedidoRequestParaPedidoEntity() {

        PedidoRequest request = Dummies.pedidoRequest();

        ClienteEntity cliente = Dummies.clienteEntity();

        List<ItemPedidoEntity> itens =
                PedidoMapper.INSTANCE.toItemPedidoEntities(
                        Dummies.produtosVO(),
                        request.getItens());

        PedidoEntity entity =
                PedidoMapper.INSTANCE.toPedidoEntity(
                        request,
                        cliente,
                        itens,
                        new BigDecimal("119.80"),
                        BigDecimal.ZERO,
                        new BigDecimal("17.97"),
                        StatusPedidoEnum.PAGO,
                        new BigDecimal("101.83")
                );

        assertNotNull(entity);

        assertNull(entity.getId());

        assertEquals(cliente, entity.getCliente());

        assertEquals(itens, entity.getItens());

        assertEquals(
                new BigDecimal("119.80"),
                entity.getSubtotal());

        assertEquals(
                new BigDecimal("17.97"),
                entity.getDesconto());

        assertEquals(
                BigDecimal.ZERO,
                entity.getFrete());

        assertEquals(
                new BigDecimal("101.83"),
                entity.getTotal());

        assertEquals(
                request.getCupom(),
                entity.getCupom());
    }


    @Test
    void deveConverterPedidoEntityParaPedidoVO() {

        PedidoEntity entity = Dummies.pedidoEntity();

        PedidoVO vo =
                PedidoMapper.INSTANCE.toPedidoVO(entity);

        assertNotNull(vo);

        assertEquals(entity.getId(), vo.getId());

        assertEquals(
                entity.getCliente().getNome(),
                vo.getNomeCliente());

        assertEquals(
                entity.getSubtotal(),
                vo.getSubtotal());

        assertEquals(
                entity.getDesconto(),
                vo.getDesconto());

        assertEquals(
                entity.getFrete(),
                vo.getFrete());

        assertEquals(
                entity.getTotal(),
                vo.getTotal());

        assertEquals(
                entity.getCupom(),
                vo.getCupom());

        assertEquals(
                entity.getStatus(),
                vo.getStatus());

        assertEquals(
                entity.getPontosGerados(),
                vo.getPontosGerados());

        assertEquals(1, vo.getItens().size());

        assertEquals(
                entity.getItens().get(0).getId(),
                vo.getItens().get(0).getId());

        assertEquals(
                entity.getItens().get(0).getProduto().getId(),
                vo.getItens().get(0).getProdutoId());

        assertEquals(
                entity.getItens().get(0).getQuantidade(),
                vo.getItens().get(0).getQuantidade());

        assertEquals(
                entity.getItens().get(0).getPrecoUnitario(),
                vo.getItens().get(0).getPrecoUnitario());
    }

}