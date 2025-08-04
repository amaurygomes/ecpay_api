package br.com.amaurygomes.ecpay.gateway.mercadopago;

public record MercadoPagoPixPaymentResponse(
        String transactionId,
        String qrCodeBase64,
        String qrCode
) {
}
