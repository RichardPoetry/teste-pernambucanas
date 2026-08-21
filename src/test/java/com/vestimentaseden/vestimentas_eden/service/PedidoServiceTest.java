package com.vestimentaseden.vestimentas_eden.service;

import com.vestimentaseden.vestimentas_eden.dummies.Dummies;
import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.PedidoVO;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deveCriarPedidoCasoAClienteComumSemCupom() {

        PedidoRequest request = Dummies.pedidoRequestCasoA();

        ClienteVO cliente = Dummies.clienteComum();

        List<ProdutoVO> produtos = Dummies.produtosCasoA();

        when(pedidoRepository.save(any(PedidoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PedidoVO pedido = pedidoService.criarPedido(
                request,
                cliente,
                produtos
        );

        assertAll(
                () -> assertEquals(new BigDecimal("180.00"), pedido.getSubtotal()),
                () -> assertEquals(BigDecimal.ZERO.setScale(2), pedido.getDesconto()),
                () -> assertEquals(new BigDecimal("20.00"), pedido.getFrete()),
                () -> assertEquals(new BigDecimal("200.00"), pedido.getTotal())
        );

        verify(pedidoRepository).save(any(PedidoEntity.class));
    }

    @Test
    void deveCriarPedidoCasoBClientePlusComCupomFreteGratis() {

        PedidoRequest request = Dummies.pedidoRequestCasoB();

        ClienteVO cliente = Dummies.clientePlus();

        List<ProdutoVO> produtos = Dummies.produtosCasoB();

        when(pedidoRepository.save(any(PedidoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PedidoVO pedido = pedidoService.criarPedido(
                request,
                cliente,
                produtos
        );

        assertAll(
                () -> assertEquals(new BigDecimal("300.00"), pedido.getSubtotal()),
                () -> assertEquals(new BigDecimal("15.00"), pedido.getDesconto()),
                () -> assertEquals(BigDecimal.ZERO.setScale(2), pedido.getFrete()),
                () -> assertEquals(new BigDecimal("285.00"), pedido.getTotal())
        );

        verify(pedidoRepository).save(any(PedidoEntity.class));
    }

    @Test
    void deveCriarPedidoCasoCClienteComumComCupomDesc10() {

        PedidoRequest request = Dummies.pedidoRequestCasoC();

        ClienteVO cliente = Dummies.clienteComum();

        List<ProdutoVO> produtos = Dummies.produtosCasoC();

        when(pedidoRepository.save(any(PedidoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PedidoVO pedido = pedidoService.criarPedido(
                request,
                cliente,
                produtos
        );

        assertAll(
                () -> assertEquals(new BigDecimal("100.00"), pedido.getSubtotal()),
                () -> assertEquals(new BigDecimal("10.00"), pedido.getDesconto()),
                () -> assertEquals(new BigDecimal("20.00"), pedido.getFrete()),
                () -> assertEquals(new BigDecimal("110.00"), pedido.getTotal())
        );

        verify(pedidoRepository).save(any(PedidoEntity.class));
    }


    @Test
    void deveConsultarPedido() {

        PedidoEntity entity = Dummies.pedidoEntity();

        when(pedidoRepository.findById("PED001"))
                .thenReturn(Optional.of(entity));

        PedidoVO pedido =
                pedidoService.consultarPedido("PED001");

        assertNotNull(pedido);

        assertEquals("PED001", pedido.getId());

        assertEquals("Richard", pedido.getNomeCliente());

        verify(pedidoRepository)
                .findById("PED001");
    }
}