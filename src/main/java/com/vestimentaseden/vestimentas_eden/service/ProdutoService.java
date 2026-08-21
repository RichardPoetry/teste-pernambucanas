package com.vestimentaseden.vestimentas_eden.service;

import com.vestimentaseden.vestimentas_eden.model.pedido.request.ItemRequest;
import com.vestimentaseden.vestimentas_eden.model.mapper.ProdutoMapper;
import com.vestimentaseden.vestimentas_eden.model.pedido.vo.ItemPedidoVO;
import com.vestimentaseden.vestimentas_eden.model.produto.response.ProdutoResponse;
import com.vestimentaseden.vestimentas_eden.model.produto.vo.ProdutoVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import com.vestimentaseden.vestimentas_eden.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponse> trazerTodosProdutos() {

        List<ProdutoEntity> produto = produtoRepository.findAll();

        return produto.stream()
                .filter(produtoEntity -> produtoEntity.getEstoque() > 0)
                .map(ProdutoMapper.INSTANCE::toProdutoResponse)
                .toList();
    }

    public List<ProdutoVO> verificarItemEstoque(List<ItemRequest> itens) {

        List<ProdutoEntity> produtos = new ArrayList<>();

        for (ItemRequest item : itens) {

            ProdutoEntity produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produto não encontrado: " + item.getProdutoId()));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException(
                        "Estoque insuficiente para o produto " + produto.getNome());
            }

            produtos.add(produto);
        }

        return ProdutoMapper.INSTANCE.toListProdutoVO(produtos);
    }

    public void atualizaEstoquePedidoCriado(List<ItemRequest> itens) {

        for (ItemRequest item : itens) {

            ProdutoEntity produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produto não encontrado: " + item.getProdutoId()));

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
        }
        System.out.println("produtos atualizados no estoque");
    }


    public void atualizaEstoquePedidoCancelado(List<ItemPedidoVO> itens) {

        for (ItemPedidoVO item : itens) {

            ProdutoEntity produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produto não encontrado: " + item.getProdutoId()));

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
        }
        System.out.println("produtos devolvidos ao estoque");
    }
}
