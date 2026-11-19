package com.omnicart.payment.controller;

import com.omnicart.payment.dto.request.CreatePaymentOrderRequestDto;
import com.omnicart.payment.dto.request.PaymentOrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ranjit Soni on 19-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity<PaymentOrderResponseDto> initiatePayment(@RequestBody CreatePaymentOrderRequestDto createPaymentOrderRequestDto) {
        return null;
    }


    @PutMapping
    public ResponseEntity<PaymentOrderResponseDto> verifyPayment() {
        return null;
    }

    @GetMapping
    public void paymentCallback() {

    }

    public ResponseEntity<PaymentOrderResponseDto> initiateRefund() {
        return null;
    }

}
