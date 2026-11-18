package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateInventoryRequestDto;
import com.omnicart.product.dto.request.ReserveInventoryQuantityRequestDto;
import com.omnicart.product.dto.request.UpdateInventoryRequestDto;
import com.omnicart.product.dto.response.InventoryResponseDto;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.service.IInventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_INVENTORY')")
    public ResponseEntity<InventoryResponseDto> createInventory(@Valid @RequestBody CreateInventoryRequestDto request)
            throws CustomBusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_INVENTORY')")
    public ResponseEntity<InventoryResponseDto> updateInventory(@PathVariable long id, @Valid @RequestBody UpdateInventoryRequestDto request)
            throws CustomBusinessException {
        return ResponseEntity.ok(inventoryService.updateInventory(id, request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_INVENTORY')")
    public ResponseEntity<InventoryResponseDto> getInventory(@PathVariable long id) throws CustomBusinessException {
        return ResponseEntity.ok(inventoryService.getInventory(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_INVENTORY')")
    public ResponseEntity<List<InventoryResponseDto>> getInventories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(inventoryService.getInventories(pageNumber, pageSize));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_INVENTORY')")
    public ResponseEntity<Void> deleteInventory(@PathVariable long id) throws CustomBusinessException {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productVariantId}/{vendorId}")
    @PreAuthorize("hasAuthority('READ_INVENTORY')")
    public ResponseEntity<InventoryResponseDto> getInventory(@PathVariable Long productVariantId, @PathVariable Long vendorId)
            throws CustomBusinessException {
        return ResponseEntity.ok(inventoryService.getInventory(productVariantId, vendorId));
    }

    @PutMapping("/reserve")
    @PreAuthorize("hasAuthority('RESERVE_INVENTORY')")
    public ResponseEntity<List<InventoryResponseDto>> reserveInventory(@NotNull @NotEmpty @RequestBody List<ReserveInventoryQuantityRequestDto> reserveInventoryRequests) {
        return ResponseEntity.ok(inventoryService.reserveInventory(reserveInventoryRequests));
    }
}
