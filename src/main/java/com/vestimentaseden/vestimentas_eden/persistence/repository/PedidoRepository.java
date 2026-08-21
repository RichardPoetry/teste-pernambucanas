package com.vestimentaseden.vestimentas_eden.persistence.repository;

import com.vestimentaseden.vestimentas_eden.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}