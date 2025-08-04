package br.com.amaurygomes.ecpay.features.payment.controller;

import br.com.amaurygomes.ecpay.features.payment.service.PaymentService;
import br.com.amaurygomes.ecpay.gateway.mercadopago.MercadoPagoGeneratePixPayment;
import br.com.amaurygomes.ecpay.gateway.mercadopago.MercadoPagoPixPaymentRequest;
import br.com.amaurygomes.ecpay.gateway.mercadopago.MercadoPagoPixPaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment/generate_pix")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final MercadoPagoGeneratePixPayment mercadoPagoGeneratePixPayment;

    @PostMapping("/mercadopago")
    public ResponseEntity<MercadoPagoPixPaymentResponse> generatePixPayment(@RequestBody @Valid MercadoPagoPixPaymentRequest request){
        return ResponseEntity.ok(mercadoPagoGeneratePixPayment.generatePix(request.userId(), request.billingCycle()));
    }

}
