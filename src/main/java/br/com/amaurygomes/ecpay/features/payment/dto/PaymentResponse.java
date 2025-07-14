package br.com.amaurygomes.ecpay.features.payment.dto;

import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import br.com.amaurygomes.ecpay.features.user.entity.DeliveryUser;

import java.time.LocalDateTime;

public record PaymentResponse(
        String transactionId,
        Double amount,
        PaymentStatus status,
        String observation,
        DeliveryUser deliveryUser,
        LocalDateTime paymentDate,
        LocalDateTime creationDate,
        LocalDateTime updateDate
) {

    public static PaymentResponse fromEntity(Payment payment) {
        return new PaymentResponse(
                payment.getTransactionId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getObservation(),
                payment.getDeliveryUser(),
                payment.getPaymentDate(),
                payment.getCreationDate(),
                payment.getUpdateDate()
        );
    }

}
