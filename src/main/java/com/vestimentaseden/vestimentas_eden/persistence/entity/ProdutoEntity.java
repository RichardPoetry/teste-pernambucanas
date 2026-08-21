package com.vestimentaseden.vestimentas_eden.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "estoque")
    private int estoque;
}
