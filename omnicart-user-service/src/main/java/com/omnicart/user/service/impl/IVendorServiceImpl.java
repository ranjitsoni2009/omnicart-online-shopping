package com.omnicart.user.service.impl;

import com.omnicart.user.dto.request.CreateVendorRequestDto;
import com.omnicart.user.dto.request.UpdateVendorRequestDto;
import com.omnicart.user.dto.response.VendorResponseDto;
import com.omnicart.user.repo.IVendorRepo;
import com.omnicart.user.service.IVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranjit Soni on 28-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Service
@RequiredArgsConstructor
public class IVendorServiceImpl implements IVendorService  {

    private final IVendorRepo vendorRepo;

    @Override
    public VendorResponseDto registerVendor(CreateVendorRequestDto vendorRequestDto) {
        return null;
    }

    @Override
    public VendorResponseDto updateVendorRegistration(UpdateVendorRequestDto vendorRequestDto) {
        return null;
    }

    @Override
    public List<VendorResponseDto> getVendors(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public VendorResponseDto getVendor(long vendorId) {
        return null;
    }

    @Override
    public void deleteVendor(long vendorId) {

    }
}
