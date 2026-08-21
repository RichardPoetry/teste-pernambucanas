package com.vestimentaseden.vestimentas_eden.model.mapper;

import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE =  Mappers.getMapper(ClienteMapper.class);

    ClienteVO toClienteVO(ClienteEntity clienteEntity);

    ClienteEntity toClienteEntity(ClienteVO cliente);
}
