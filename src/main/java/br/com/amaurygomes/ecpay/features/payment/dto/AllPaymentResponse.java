package br.com.amaurygomes.ecpay.features.payment.dto;

import br.com.amaurygomes.ecpay.features.payment.entity.BillingCycle;
import br.com.amaurygomes.ecpay.features.payment.entity.Payment;

import java.time.LocalDateTime;

public record AllPaymentResponse(
        String transactionId,
        Double amount,
        BillingCycle billingCycle,
        String status,
        String observation,
        String deliveryUserId,
        LocalDateTime paymentDate,
        LocalDateTime creationDate,
        LocalDateTime updateDate
) {

    public static AllPaymentResponse fromEntity(Payment payment) {
        return new AllPaymentResponse(
                payment.getTransactionId(),
                payment.getAmount(),
                payment.getBillingCycle(),
                payment.getStatus().name(),
                payment.getObservation(),
                payment.getDeliveryUser().getId().toString(),
                payment.getPaymentDate(),
                payment.getCreationDate(),
                payment.getUpdateDate()
        );
    }
}
