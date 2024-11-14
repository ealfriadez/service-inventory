package com.codearqui.serviceinventory.expose;

import com.codearqui.serviceinventory.api.ApiUtil;
import com.codearqui.serviceinventory.api.InventoryApi;
import com.codearqui.serviceinventory.api.InventoryApiDelegate;
import com.codearqui.serviceinventory.dto.InventoryRequest;
import com.codearqui.serviceinventory.dto.InventoryResponse;
import com.codearqui.serviceinventory.dto.OrderInvRequest;
import com.codearqui.serviceinventory.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
@AllArgsConstructor
public class InventoryApiImpl implements InventoryApiDelegate {
    private final InventoryService inventoryService;
    /**
     * GET /inventories/{productId} : Obtener el inventario de un producto en especifico
     *
     * @param productId  (required)
     * @return Detalles del inventario del producto (status code 200)
     *         or Inventario no encontrado (status code 404)
     * @see InventoryApi#getInventory
     */
    @Override
    public Mono<InventoryResponse> getInventory(String productId,
                                                 ServerWebExchange exchange) {
        return inventoryService.getInventory(productId);
    }

    /**
     * GET /inventories : Listar todos los inventarios
     *
     * @return Lista de inventarios (status code 200)
     * @see InventoryApi#listInventory
     */
    @Override
    public Flux<InventoryResponse> listInventory(ServerWebExchange exchange) {
        return inventoryService.getList()
                .delayElements(Duration.ofSeconds(2));
    }

    /**
     * POST /inventories : Registrar el inventario para una nueva orden
     *
     * @param inventoryRequest  (required)
     * @return Inventario registrado exitosamente (status code 201)
     * @see InventoryApi#registerInventory
     */
    @Override
    public Mono<InventoryResponse> registerInventory(Mono<InventoryRequest> inventoryRequest,
                                                      ServerWebExchange exchange) {
        return inventoryService.createOrder(inventoryRequest);
    }
}
