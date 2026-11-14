package com.omnicart.user.controller;

import com.omnicart.user.dto.request.CreateVendorRequestDto;
import com.omnicart.user.dto.request.UpdateVendorRequestDto;
import com.omnicart.user.dto.response.VendorResponseDto;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.service.IVendorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ranjit Soni on 28-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final IVendorService vendorService;

    @PreAuthorize("hasAuthority('CREATE_VENDOR')")
    @PostMapping
    public ResponseEntity<VendorResponseDto> registerVendor(@Validated @RequestBody CreateVendorRequestDto createVendorRequestDto) throws CustomBusinessException {
        VendorResponseDto vendorResponseDto = vendorService.registerVendor(createVendorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorResponseDto);
    }

    @PutMapping
    public ResponseEntity<VendorResponseDto> updateVendorRegistration(@RequestBody UpdateVendorRequestDto updateVendorRequestDto) throws CustomBusinessException {
        VendorResponseDto vendorResponseDto = vendorService.updateVendorRegistration(updateVendorRequestDto);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<VendorResponseDto>> getVendors(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<VendorResponseDto> vendorResponseDtoList = vendorService.getVendors(pageNumber, pageSize);
        return ResponseEntity.ok(vendorResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDto> getVendor(@PathVariable("id") long vendorId) throws CustomBusinessException {
        VendorResponseDto vendorResponseDto = vendorService.getVendor(vendorId);
        return ResponseEntity.ok(vendorResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") long vendorId) throws CustomBusinessException {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
