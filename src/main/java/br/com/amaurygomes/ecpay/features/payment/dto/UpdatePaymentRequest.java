package br.com.amaurygomes.ecpay.features.payment.dto;

import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentRequest(
        @NotNull(message = "Status cannot be null")
        PaymentStatus status
) {
}
