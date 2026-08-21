package com.vestimentaseden.vestimentas_eden.model.produto.mapper;

import com.vestimentaseden.vestimentas_eden.model.produto.response.ProdutoResponse;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    ProdutoResponse toProdutoResponse(ProdutoEntity produto);

}
