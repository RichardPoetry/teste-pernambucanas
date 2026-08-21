package com.vestimentaseden.vestimentas_eden.model.pedido.mapper;

import com.vestimentaseden.vestimentas_eden.model.pedido.StatusPedidoEnum;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.ItemRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.request.PedidoRequest;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.ItemPedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.PedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ItemPedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANCE =  Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "clienteEntity")
    @Mapping(target = "itens", source = "itemPedidoEntities")
    @Mapping(target = "subtotal", source = "subtotal")
    @Mapping(target = "frete", source = "frete")
    @Mapping(target = "desconto", source = "desconto")
    @Mapping(target = "total", source = "total")
    @Mapping(target = "cupom", source = "pedidoRequest.cupom")
    @Mapping(target = "pontosGerados", ignore = true)
    PedidoEntity toPedidoEntity(PedidoRequest pedidoRequest,
                                ClienteEntity clienteEntity,
                                List<ItemPedidoEntity> itemPedidoEntities,
                                BigDecimal subtotal,
                                BigDecimal frete,
                                BigDecimal desconto,
                                StatusPedidoEnum statusPedidoEnum,
                                BigDecimal total);

    default List<ItemPedidoEntity> toItemPedidoEntities(List<ProdutoVO> produtos, List<ItemRequest> itens) {

        List<ItemPedidoEntity> entities = new ArrayList<>();

        for (ItemRequest item : itens) {

            ProdutoVO produto = produtos.stream()
                    .filter(p -> p.getId().equals(item.getProdutoId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado: " + item.getProdutoId()));

            ProdutoEntity produtoEntity = ProdutoEntity.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .preco(produto.getPreco())
                    .build();

            ItemPedidoEntity entity = ItemPedidoEntity.builder()
                    .produto(produtoEntity)
                    .quantidade(item.getQuantidade())
                    .precoUnitario(produto.getPreco())
                    .build();

            entities.add(entity);
        }
        return entities;
    }



    default PedidoResponse toPedidoResponse(PedidoEntity pedidoEntity){

        List<ItemPedidoResponse> itemPedidoResponse = pedidoEntity.getItens().stream()
                .map(item -> ItemPedidoResponse.builder()
                        .id(item.getId())
                        .produtoId(item.getProduto().getId())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getPrecoUnitario())
                        .build())
                .toList();


        return PedidoResponse.builder()
                .id(pedidoEntity.getId())
                .nomeCliente(pedidoEntity.getCliente().getNome())
                .itens(itemPedidoResponse)
                .subtotal(pedidoEntity.getSubtotal())
                .desconto(pedidoEntity.getDesconto())
                .frete(pedidoEntity.getFrete())
                .total(pedidoEntity.getTotal())
                .build();
    }
}
