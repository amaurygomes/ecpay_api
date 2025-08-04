package br.com.amaurygomes.ecpay.gateway.mercadopago;

import br.com.amaurygomes.ecpay.features.payment.entity.BillingCycle;

import java.util.UUID;

public record MercadoPagoPixPaymentRequest(
        UUID userId,
        BillingCycle billingCycle
) {
}
