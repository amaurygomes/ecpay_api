package br.com.amaurygomes.ecpay.features.payment.dto;

public record RefundPaymentRequest(
        String transactionId,
        String observation
) {
}
