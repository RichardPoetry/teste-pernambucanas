package com.vestimentaseden.vestimentas_eden.controller;

import com.vestimentaseden.vestimentas_eden.model.produto.response.ProdutoResponse;
import com.vestimentaseden.vestimentas_eden.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/produto")
    public List<ProdutoResponse> getProduto(){

    List<ProdutoResponse> produtoRetornado = produtoService.trazerTodosProdutos();

    return produtoRetornado;
    }

}
