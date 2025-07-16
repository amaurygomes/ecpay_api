package br.com.amaurygomes.ecpay.features.payment.controller;

import br.com.amaurygomes.ecpay.features.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    public ResponseEntity refundPayment() {
        return ResponseEntity.ok().build();
    }
}
