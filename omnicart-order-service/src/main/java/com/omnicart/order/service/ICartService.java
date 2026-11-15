package com.omnicart.order.service;

import com.omnicart.order.dto.request.CreateCartItemRequestDto;
import com.omnicart.order.dto.response.CartItemResponseDto;
import com.omnicart.order.exception.CustomBusinessException;
import com.omnicart.order.port.CartPort;
import java.util.List;

public interface ICartService {

    List<CartItemResponseDto> getCurrentCart();

    void clearCurrentCart();

    CartItemResponseDto addItemToCart(CreateCartItemRequestDto requestDto) throws CustomBusinessException;
}
