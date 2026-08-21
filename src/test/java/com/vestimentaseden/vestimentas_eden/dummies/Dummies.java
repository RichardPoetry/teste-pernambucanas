package com.vestimentaseden.vestimentas_eden.dummies;

import com.vestimentaseden.vestimentas_eden.model.cliente.TipoClienteEnum;
import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.model.pedido.CupomEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.ItemRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.ItemPedidoVO;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ItemPedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

public final class Dummies {

    public static ClienteEntity clienteEntity() {

       return ClienteEntity.builder()
                       .id("testeId")
                       .nome("Richard")
                       .tipo(TipoClienteEnum.PLUS)
                       .build();

    }

    public static ClienteVO clienteComum() {
        return new ClienteVO(
                "CLI001",
                "Richard",
                TipoClienteEnum.COMUM
        );
    }

    public static ClienteVO clientePlus() {
        return new ClienteVO(
                "CLI002",
                "Richard Plus",
                TipoClienteEnum.PLUS
        );
    }
    public static PedidoRequest pedidoRequestCasoA() {

        PedidoRequest request = new PedidoRequest();

        request.setClientId("CLI001");

        request.setCupom(null);

        request.setItens(List.of(

                new ItemRequest(
                        "P1",
                        2
                ),

                new ItemRequest(
                        "P2",
                        1
                )
        ));

        return request;
    }

    public static List<ProdutoVO> produtosCasoB() {

        ProdutoVO p3 = new ProdutoVO(
                "P3",
                "Produto Premium",
                new BigDecimal("300.00"),
                5
        );

        return List.of(p3);
    }

    public static PedidoRequest pedidoRequestCasoB() {

        PedidoRequest request = new PedidoRequest();

        request.setClientId("CLI002");

        request.setCupom(CupomEnum.FRETEGRATIS);

        request.setItens(List.of(

                new ItemRequest(
                        "P3",
                        1
                )
        ));

        return request;
    }


    public static List<ProdutoVO> produtosCasoA() {

        ProdutoVO p1 = new ProdutoVO(
                "P1",
                "Produto 1",
                new BigDecimal("50.00"),
                10
        );

        ProdutoVO p2 = new ProdutoVO(
                "P2",
                "Produto 2",
                new BigDecimal("80.00"),
                10
        );

        return List.of(p1, p2);
    }

    public static List<ProdutoVO> produtosCasoC() {

        ProdutoVO p1 = new ProdutoVO(
                "P1",
                "Produto 1",
                new BigDecimal("50.00"),
                10
        );

        return List.of(p1);
    }

    public static PedidoRequest pedidoRequestCasoC() {

        PedidoRequest request = new PedidoRequest();

        request.setClientId("CLI001");

        request.setCupom(CupomEnum.DESC10);

        request.setItens(List.of(

                new ItemRequest(
                        "P1",
                        2
                )
        ));

        return request;
    }

    public static ProdutoEntity produtoEntity() {

        return ProdutoEntity.builder()
                .id("PROD001")
                .nome("Camiseta")
                .categoria("Masculino")
                .preco(new BigDecimal("59.90"))
                .estoque(20)
                .build();
    }

    public static ProdutoVO produtoVO() {

        return new ProdutoVO(
                "PROD001",
                "Camiseta",
                new BigDecimal("59.90"),
                20
        );
    }

    public static List<ProdutoVO> produtosVO() {

        return List.of(produtoVO());
    }


    public static ItemRequest itemRequest() {

        return new ItemRequest(
                "PROD001",
                2
        );
    }

    public static List<ItemRequest> itensRequest() {

        return List.of(itemRequest());
    }


    public static PedidoRequest pedidoRequest() {

        PedidoRequest request = new PedidoRequest();

        request.setClientId("CLI001");
        request.setCupom(CupomEnum.DESC10);
        request.setItens(itensRequest());

        return request;
    }


    public static ItemPedidoEntity itemPedidoEntity() {

        return ItemPedidoEntity.builder()
                .id("ITEM001")
                .produto(produtoEntity())
                .quantidade(2)
                .precoUnitario(new BigDecimal("59.90"))
                .build();
    }

    public static List<ItemPedidoEntity> itensPedidoEntity() {

        return List.of(itemPedidoEntity());
    }


    public static ItemPedidoVO itemPedidoVO() {

        return ItemPedidoVO.builder()
                .id("ITEM001")
                .produtoId("PROD001")
                .quantidade(2)
                .precoUnitario(new BigDecimal("59.90"))
                .build();
    }

    public static List<ItemPedidoVO> itensPedidoVO() {

        return List.of(itemPedidoVO());
    }

    public static PedidoEntity pedidoEntity() {

        PedidoEntity entity = new PedidoEntity();

        entity.setId("PED001");
        entity.setCliente(clienteEntity());
        entity.setItens(itensPedidoEntity());

        entity.setSubtotal(new BigDecimal("120.00"));
        entity.setDesconto(new BigDecimal("17.00"));
        entity.setFrete(BigDecimal.ZERO);
        entity.setTotal(new BigDecimal("103"));

        entity.setCupom(CupomEnum.DESC10);
        entity.setStatus(StatusPedidoEnum.PAGO);
        entity.setPontosGerados(206);

        return entity;
    }

}
