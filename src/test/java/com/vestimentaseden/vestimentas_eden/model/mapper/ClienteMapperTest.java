package com.vestimentaseden.vestimentas_eden.model.mapper;

import com.vestimentaseden.vestimentas_eden.dummies.Dummies;
import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ClienteMapperTest {

    @Test
    void deveConverterClienteEntityParaClienteVO() {

        ClienteEntity entity = Dummies.clienteEntity();

        ClienteVO vo = ClienteMapper.INSTANCE.toClienteVO(entity);

        assertNotNull(vo);

        assertEquals(entity.getId(), vo.getId());
        assertEquals(entity.getNome(), vo.getNome());
        assertEquals(entity.getTipo(), vo.getTipo());
    }

    @Test
    void deveConverterClienteVOParaClienteEntity() {

        ClienteVO vo = Dummies.clientePlus();

        ClienteEntity entity = ClienteMapper.INSTANCE.toClienteEntity(vo);

        assertNotNull(entity);

        assertEquals(vo.getId(), entity.getId());
        assertEquals(vo.getNome(), entity.getNome());
        assertEquals(vo.getTipo(), entity.getTipo());
    }
  
}