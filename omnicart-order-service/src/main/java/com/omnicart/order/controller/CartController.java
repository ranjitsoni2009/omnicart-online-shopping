package com.omnicart.order.controller;

import com.omnicart.order.dto.request.CreateCartItemRequestDto;
import com.omnicart.order.dto.response.CartItemResponseDto;
import com.omnicart.order.exception.CustomBusinessException;
import com.omnicart.order.port.CartPort;
import com.omnicart.order.service.ICartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @PreAuthorize("hasAuthority('ADD_ITEM_IN_CART')")
    @PostMapping
    public ResponseEntity<CartItemResponseDto> addItemToCart(@RequestBody CreateCartItemRequestDto requestDto) throws CustomBusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addItemToCart(requestDto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_OWN_CART')")
    public List<CartItemResponseDto> getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('CLEAR_OWN_CART')")
    public ResponseEntity<Void> clearCurrentCart() {
        cartService.clearCurrentCart();
        return ResponseEntity.noContent().build();
    }
}
