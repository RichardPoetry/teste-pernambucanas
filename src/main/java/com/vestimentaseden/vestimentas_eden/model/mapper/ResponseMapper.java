package com.vestimentaseden.vestimentas_eden.model.mapper;

import com.vestimentaseden.vestimentas_eden.model.pedido.response.ItemPedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.pedido.response.PedidoResponse;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.ItemPedidoVO;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.PedidoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    PedidoResponse toResponse(PedidoVO pedidoVO);

    ItemPedidoResponse toResponse(ItemPedidoVO itemVO);

}
