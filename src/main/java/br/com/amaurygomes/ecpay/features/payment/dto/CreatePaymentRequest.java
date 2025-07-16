package br.com.amaurygomes.ecpay.features.payment.dto;

import br.com.amaurygomes.ecpay.features.payment.entity.BillingCycle;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreatePaymentRequest (
        @NotNull(message = "Transaction ID cannot be null")
        String transactionId,
        @NotNull(message = "Amount cannot be null")
        @Size(min = 1, message = "Amount must be greater than 0")
        Double amount,
        @NotNull(message = "Billing Cycle cannot be null")
        BillingCycle billingCycle,
        @NotNull(message = "Delivery User ID cannot be null")
        String deliveryUserId
){
}
