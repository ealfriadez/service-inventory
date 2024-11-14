package com.codearqui.serviceinventory.model.dto;

import com.codearqui.serviceinventory.dto.InventoryRequest;
import com.codearqui.serviceinventory.dto.InventoryResponse;
import com.codearqui.serviceinventory.model.entity.ProductsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(target = "code", source = "idProduct")
    ProductsModel requestToModel(InventoryRequest inventoryRequest);

    @Mapping(target = "idProduct", source = "code")
    InventoryResponse modelToResponse(ProductsModel productsModel);
}
