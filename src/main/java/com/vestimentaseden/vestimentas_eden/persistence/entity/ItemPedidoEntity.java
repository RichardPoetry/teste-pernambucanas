package com.vestimentaseden.vestimentas_eden.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemPedidoEntity {

    @Id
    @UuidGenerator
    private String id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    private Integer quantidade;

    private BigDecimal precoUnitario;

}
