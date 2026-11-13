package com.omnicart.user.service;

import com.omnicart.user.dto.request.CreateVendorRequestDto;
import com.omnicart.user.dto.request.UpdateVendorRequestDto;
import com.omnicart.user.dto.response.VendorResponseDto;
import com.omnicart.user.exception.CustomBusinessException;

import java.util.List;

public interface IVendorService {

    VendorResponseDto registerVendor(CreateVendorRequestDto vendorRequestDto) throws CustomBusinessException;

    VendorResponseDto updateVendorRegistration(UpdateVendorRequestDto vendorRequestDto) throws CustomBusinessException;

    List<VendorResponseDto> getVendors(int pageNumber, int pageSize);

    VendorResponseDto getVendor(long vendorId) throws CustomBusinessException;

    void deleteVendor(long vendorId) throws CustomBusinessException;
}
