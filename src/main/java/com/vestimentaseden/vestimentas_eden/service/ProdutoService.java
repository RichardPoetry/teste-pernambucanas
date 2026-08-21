package com.vestimentaseden.vestimentas_eden.service;

import com.vestimentaseden.vestimentas_eden.model.produto.mapper.ProdutoMapper;
import com.vestimentaseden.vestimentas_eden.model.produto.response.ProdutoResponse;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponse> trazerProduto() {

        List<ProdutoEntity> produto = produtoRepository.findAll();

        return produto.stream()
                .filter(produtoEntity -> produtoEntity.getEstoque() > 0)
                .map(ProdutoMapper.INSTANCE::toProdutoResponse)
                .toList();
    }
}
