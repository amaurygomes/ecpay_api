package br.com.amaurygomes.ecpay.gateway.mercadopago;

import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class MercadoPagoGateway {
    @Value("${mercadopago.webhook.url}")
    private String WEBHOOK_URL;

    public com.mercadopago.resources.payment.Payment createPixPayment(BigDecimal amount, String observation, String transactionId, String userEmail) throws MPException, MPApiException{
        PaymentClient client = new PaymentClient();
        PaymentCreateRequest request = PaymentCreateRequest.builder()
                .transactionAmount(amount)
                .description(observation)
                .externalReference(transactionId)
                .paymentMethodId("pix")
                .dateOfExpiration(generateExpirationDate())
                .payer(PaymentPayerRequest.builder().email(userEmail).build())
                .notificationUrl(WEBHOOK_URL)
                .build();
        return client.create(request);
    }

    public com.mercadopago.resources.payment.Payment getPaymentDetails(Long paymentId) throws MPException, MPApiException {
        PaymentClient client = new PaymentClient();
        return client.get(paymentId);
    }

    private OffsetDateTime generateExpirationDate() {
        return OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(30);
    }

}
