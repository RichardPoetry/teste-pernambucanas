package com.vestimentaseden.vestimentas_eden.model.mapper;


import com.vestimentaseden.vestimentas_eden.dummies.Dummies;
import com.vestimentaseden.vestimentas_eden.model.produto.response.ProdutoResponse;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoMapperTest {

    @Test
    void deveConverterProdutoEntityParaProdutoVO() {

        ProdutoEntity entity = Dummies.produtoEntity();

        ProdutoVO vo = ProdutoMapper.INSTANCE.toProdutoVO(entity);

        assertNotNull(vo);

        assertEquals(entity.getId(), vo.getId());
        assertEquals(entity.getNome(), vo.getNome());
        assertEquals(entity.getPreco(), vo.getPreco());
        assertEquals(entity.getEstoque(), vo.getEstoque());
    }

    @Test
    void deveConverterProdutoEntityParaProdutoResponse() {

        ProdutoEntity entity = Dummies.produtoEntity();

        ProdutoResponse response = ProdutoMapper.INSTANCE.toProdutoResponse(entity);

        assertNotNull(response);

        assertEquals(entity.getNome(), response.getNome());
        assertEquals(entity.getCategoria(), response.getCategoria());

        assertEquals(
                entity.getPreco().doubleValue(),
                response.getPreco()
        );

        assertEquals(
                String.valueOf(entity.getEstoque()),
                response.getEstoque()
        );
    }

    @Test
    void deveConverterListaProdutoEntityParaListaProdutoVO() {

        List<ProdutoEntity> entities = List.of(
                Dummies.produtoEntity()
        );

        List<ProdutoVO> lista = ProdutoMapper.INSTANCE.toListProdutoVO(entities);

        assertNotNull(lista);

        assertEquals(1, lista.size());

        ProdutoVO vo = lista.get(0);

        assertEquals("PROD001", vo.getId());
        assertEquals("Camiseta", vo.getNome());
        assertEquals(20, vo.getEstoque());
        assertEquals(0,
                vo.getPreco().compareTo(entityPreco()));
    }

    private static java.math.BigDecimal entityPreco() {
        return new java.math.BigDecimal("59.90");
    }

}