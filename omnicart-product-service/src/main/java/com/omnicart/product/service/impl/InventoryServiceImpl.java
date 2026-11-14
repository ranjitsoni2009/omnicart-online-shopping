package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateInventoryRequestDto;
import com.omnicart.product.dto.request.UpdateInventoryRequestDto;
import com.omnicart.product.dto.response.InventoryResponseDto;
import com.omnicart.product.entity.Inventory;
import com.omnicart.product.entity.ProductVariant;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.repo.IInventoryRepo;
import com.omnicart.product.repo.IProductVariantRepo;
import com.omnicart.product.service.IInventoryService;
import com.omnicart.product.util.OmniCartProductConstant;
import com.omnicart.product.util.OmniCartProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private final IInventoryRepo inventoryRepo;
    private final IProductVariantRepo productVariantRepo;

    @Override
    @Transactional
    public InventoryResponseDto createInventory(CreateInventoryRequestDto request) throws CustomBusinessException {
        long adminId = authenticatedAdminId();
        if (inventoryRepo.findByProductVariantIdAndVendorId(request.getProductVariantId(), request.getVendorId()).isPresent()) {
            throw businessException(OmniCartProductConstant.INVENTORY_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductConstant.INVENTORY_ALREADY_EXIST_ERROR_CODE);
        }

        ProductVariant productVariant = productVariantRepo.findById(request.getProductVariantId())
                .orElseThrow(() -> businessException(OmniCartProductConstant.PRODUCT_VARIANT_NOT_FOUND,
                        OmniCartProductConstant.PRODUCT_VARIANT_NOT_FOUND_ERROR_CODE));
        Instant now = Instant.now();
        Inventory inventory = new Inventory();
        inventory.setProductVariant(productVariant);
        inventory.setVendorId(request.getVendorId());
        inventory.setAdminId(adminId);
        inventory.setQuantity(request.getQuantity());
        inventory.setReservedQuantity(0);
        inventory.setActive(true);
        inventory.setCreatedAt(now);
        inventory.setUpdatedAt(now);
        return toResponse(inventoryRepo.save(inventory));
    }

    @Override
    @Transactional
    public InventoryResponseDto updateInventory(long inventoryId, UpdateInventoryRequestDto request) throws CustomBusinessException {
        Inventory inventory = getOwnedActiveInventory(inventoryId);
        if (request.getQuantity() < inventory.getReservedQuantity()) {
            throw businessException(OmniCartProductConstant.INVALID_INVENTORY_QUANTITY,
                    OmniCartProductConstant.INVALID_INVENTORY_QUANTITY_ERROR_CODE);
        }
        inventory.setQuantity(request.getQuantity());
        inventory.setUpdatedAt(Instant.now());
        return toResponse(inventoryRepo.save(inventory));
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponseDto getInventory(long inventoryId) throws CustomBusinessException {
        return toResponse(getOwnedActiveInventory(inventoryId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDto> getInventories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return inventoryRepo.findByAdminIdAndIsActiveTrue(authenticatedAdminId(), pageable)
                .map(this::toResponse)
                .getContent();
    }

    @Override
    @Transactional
    public void deleteInventory(long inventoryId) throws CustomBusinessException {
        Inventory inventory = getOwnedActiveInventory(inventoryId);
        inventory.setActive(false);
        inventory.setUpdatedAt(Instant.now());
        inventoryRepo.save(inventory);
    }

    private Inventory getOwnedActiveInventory(long inventoryId) throws CustomBusinessException {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> businessException(OmniCartProductConstant.INVENTORY_NOT_FOUND,
                        OmniCartProductConstant.INVENTORY_NOT_FOUND_ERROR_CODE));
        if (!inventory.isActive()) {
            throw businessException(OmniCartProductConstant.INVENTORY_NOT_FOUND,
                    OmniCartProductConstant.INVENTORY_NOT_FOUND_ERROR_CODE);
        }
        if (inventory.getAdminId() != authenticatedAdminId()) {
            throw businessException(OmniCartProductConstant.INVENTORY_ACCESS_DENIED,
                    OmniCartProductConstant.INVENTORY_ACCESS_DENIED_ERROR_CODE);
        }
        return inventory;
    }

    private long authenticatedAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Authenticated administrator is required");
        }
        return Long.parseLong(authentication.getName());
    }

    private InventoryResponseDto toResponse(Inventory inventory) {
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .productVariantId(inventory.getProductVariant().getId())
                .vendorId(inventory.getVendorId())
                .quantity(inventory.getQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .availableQuantity(inventory.getQuantity() - inventory.getReservedQuantity())
                .active(inventory.isActive())
                .build();
    }

    private CustomBusinessException businessException(String message, String code) {
        return new CustomBusinessException(message,
                OmniCartProductUtil.getErrorCode(OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, code));
    }
}
