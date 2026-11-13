package com.omnicart.user.service.impl;

import com.omnicart.user.dto.request.CreateVendorRequestDto;
import com.omnicart.user.dto.request.UpdateVendorRequestDto;
import com.omnicart.user.dto.response.VendorResponseDto;
import com.omnicart.user.entity.VendorDetail;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.mapper.GenericMapper;
import com.omnicart.user.repo.IVendorRepo;
import com.omnicart.user.service.IVendorService;
import com.omnicart.user.util.OmniCartUserConstant;
import com.omnicart.user.util.OmniCartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ranjit Soni on 28-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Service
@RequiredArgsConstructor
public class IVendorServiceImpl implements IVendorService  {

    private final IVendorRepo vendorRepo;
    private final GenericMapper genericMapper;

    @Override
    public VendorResponseDto registerVendor(CreateVendorRequestDto vendorRequestDto) throws CustomBusinessException {
        Optional<VendorDetail> optionalVendorDetail = vendorRepo.findByBusinessName(vendorRequestDto.getBusinessName());
        if (optionalVendorDetail.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartUserConstant.VENDOR_ALREADY_REGISTERED_ERROR_CODE,
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.BUSINESS_EXCEPTION_TAG, OmniCartUserConstant.VENDOR_ALREADY_REGISTERED_ERROR_CODE));
        }

        VendorDetail vendorDetail = genericMapper.toEntity(vendorRequestDto, VendorDetail.class);
        vendorDetail.setCreatedAt(Instant.now());
        vendorDetail.setUpdatedAt(Instant.now());
        return genericMapper.toDto(vendorDetail, VendorResponseDto.class);
    }

    @Override
    public VendorResponseDto updateVendorRegistration(UpdateVendorRequestDto vendorRequestDto) throws CustomBusinessException {
        VendorDetail vendorDetail = getVendorById(vendorRequestDto.getId());
        vendorDetail.setBusinessName(vendorRequestDto.getBusinessName());
        vendorDetail.setRegistrationNumber(vendorRequestDto.getRegistrationNumber());
        vendorDetail.setContactNumber(vendorRequestDto.getContactNumber());
        vendorDetail.setAddress(vendorRequestDto.getAddress());
        vendorDetail.setUpdatedAt(Instant.now());
        vendorDetail = vendorRepo.save(vendorDetail);
        return genericMapper.toDto(vendorDetail, VendorResponseDto.class);
    }

    @Override
    public List<VendorResponseDto> getVendors(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VendorDetail> vendorDetails = vendorRepo.findAll(pageable);
        List<VendorDetail> vendorDetailList = vendorDetails.getContent();

        return vendorDetailList.stream()
                .map(vendorDetail ->
                        genericMapper.toDto(vendorDetail, VendorResponseDto.class))
                .toList();
    }

    @Override
    public VendorResponseDto getVendor(long vendorId) throws CustomBusinessException {
        VendorDetail vendorDetail = getVendorById(vendorId);
        return genericMapper.toDto(vendorDetail, VendorResponseDto.class);
    }

    @Override
    public void deleteVendor(long vendorId) throws CustomBusinessException {
        VendorDetail vendorDetail = getVendorById(vendorId);
        vendorDetail.setActive(false);
        vendorDetail.setUpdatedAt(Instant.now());
        vendorRepo.save(vendorDetail);
    }

    private VendorDetail getVendorById(long vendorId) throws CustomBusinessException {
        return vendorRepo.findById(vendorId)
                .orElseThrow(() ->
                        new CustomBusinessException(
                                OmniCartUserConstant.VENDOR_NOT_FOUND,
                                OmniCartUtil.getErrorCode(OmniCartUserConstant.BUSINESS_EXCEPTION_TAG, OmniCartUserConstant.VENDOR_NOT_FOUND_ERROR_CODE)));
    }
}