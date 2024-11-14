package com.codearqui.serviceinventory.repository;

import com.codearqui.serviceinventory.model.entity.ProductsModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InventoryRepository extends ReactiveCrudRepository<ProductsModel, Integer> {
    Mono<Boolean> existsByCode(String code);
    Mono<ProductsModel> findByCode(String code);
}
