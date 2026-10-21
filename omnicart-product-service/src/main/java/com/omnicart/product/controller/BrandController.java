package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateBrandRequestDto;
import com.omnicart.product.dto.request.UpdateBrandRequestDto;
import com.omnicart.product.dto.response.BrandResponseDto;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.service.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandController {

    private IBrandService brandService;
    
    @PreAuthorize("hasAuthority('CREATE_BRAND')")
    @PostMapping
    public ResponseEntity<BrandResponseDto> createBrand(@Validated @RequestBody CreateBrandRequestDto categoryRequestDto) throws CustomBusinessException {
        BrandResponseDto brandResponseDto = brandService.createBrand(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponseDto);
    }

    @PreAuthorize("hasAuthority('UPDATE_BRAND')")
    @PutMapping
    public ResponseEntity<BrandResponseDto> updateBrand(@Validated @RequestBody UpdateBrandRequestDto updateBrandRequestDto) {
        return  null;
    }

    @PreAuthorize("hasAuthority('READ_BRAND')")
    @GetMapping
    public ResponseEntity<BrandResponseDto> getBrands(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return  null;
    }

    @PreAuthorize("hasAuthority('READ_BRAND')")
    @GetMapping
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable("id") Long brandId) {
        return  null;
    }

    @PreAuthorize("hasAuthority('DELETE_BRAND')")
    @DeleteMapping
    public ResponseEntity<BrandResponseDto> deleteBrandById(@PathVariable("id") Long brandId) {
        return  null;
    }
}
