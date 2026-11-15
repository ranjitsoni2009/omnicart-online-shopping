package com.omnicart.product.service;

import com.omnicart.product.dto.request.CreateInventoryRequestDto;
import com.omnicart.product.dto.request.UpdateInventoryRequestDto;
import com.omnicart.product.dto.response.InventoryResponseDto;
import com.omnicart.product.exception.CustomBusinessException;

import java.util.List;

public interface IInventoryService {

    InventoryResponseDto createInventory(CreateInventoryRequestDto request) throws CustomBusinessException;

    InventoryResponseDto updateInventory(long inventoryId, UpdateInventoryRequestDto request) throws CustomBusinessException;

    InventoryResponseDto getInventory(long inventoryId) throws CustomBusinessException;

    List<InventoryResponseDto> getInventories(int pageNumber, int pageSize);

    void deleteInventory(long inventoryId) throws CustomBusinessException;
    InventoryResponseDto getInventory(long productVariantId, long vendorId) throws CustomBusinessException;
}
