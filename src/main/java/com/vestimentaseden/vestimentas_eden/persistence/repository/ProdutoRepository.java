package com.vestimentaseden.vestimentas_eden.persistence.repository;

import com.vestimentaseden.vestimentas_eden.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,String> {

    Optional<ProdutoEntity> findById(String id);

}
