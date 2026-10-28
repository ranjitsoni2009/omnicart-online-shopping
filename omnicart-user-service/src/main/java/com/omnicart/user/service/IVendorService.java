package com.omnicart.user.service;

import com.omnicart.user.dto.request.CreateVendorRequestDto;
import com.omnicart.user.dto.request.UpdateVendorRequestDto;
import com.omnicart.user.dto.response.VendorResponseDto;

import java.util.List;

public interface IVendorService {

    VendorResponseDto registerVendor(CreateVendorRequestDto vendorRequestDto);

    VendorResponseDto updateVendorRegistration(UpdateVendorRequestDto vendorRequestDto);

    List<VendorResponseDto> getVendors(int pageNumber, int pageSize);

    VendorResponseDto getVendor(long vendorId);

    void deleteVendor(long vendorId);
}
