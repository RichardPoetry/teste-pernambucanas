package com.vestimentaseden.vestimentas_eden.persistence.entity;

import com.vestimentaseden.vestimentas_eden.model.cliente.TipoClienteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoClienteEnum tipo;
}
