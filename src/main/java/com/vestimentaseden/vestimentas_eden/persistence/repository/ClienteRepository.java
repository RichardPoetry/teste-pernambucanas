package com.vestimentaseden.vestimentas_eden.persistence.repository;

import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
