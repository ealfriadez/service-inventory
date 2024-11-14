package com.codearqui.serviceinventory.service;

import com.codearqui.serviceinventory.dto.InventoryRequest;
import com.codearqui.serviceinventory.dto.InventoryResponse;
import com.codearqui.serviceinventory.model.dto.InventoryMapper;
import com.codearqui.serviceinventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * @param inventory
     * @return
     */
    @Override
    public Mono<InventoryResponse> createOrder(Mono<InventoryRequest> inventory) {
        return inventory.map(InventoryMapper.INSTANCE::requestToModel)
                .flatMap(value -> inventoryRepository.existsByCode(value.getCode())
                        .flatMap(y -> {
                            if (y){
                                return Mono.empty();
                            }
                            return inventoryRepository.save(value);
                        })
                )
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Mono<InventoryResponse> getInventory(String code) {
        return inventoryRepository.findByCode(code)
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }

    /**
     * @return
     */
    @Override
    public Flux<InventoryResponse> getList() {
        return inventoryRepository.findAll()
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }
}
