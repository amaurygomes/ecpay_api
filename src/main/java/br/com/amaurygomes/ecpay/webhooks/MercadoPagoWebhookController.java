package br.com.amaurygomes.ecpay.webhooks;

import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import br.com.amaurygomes.ecpay.features.payment.repository.PaymentRepository;
import br.com.amaurygomes.ecpay.gateway.mercadopago.MercadoPagoGateway;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
@Slf4j
public class MercadoPagoWebhookController {
    private final MercadoPagoGateway mercadoPagoGateway;
    private final PaymentRepository paymentRepository;

    @PostMapping("/mercadopago")
    @Transactional
    public ResponseEntity<Void> handleMercadoPagoWebhook(@RequestBody Map<String, Object> notification) {
        log.info("Received webhook notification:: {}", notification);

        String action = (String) notification.get("action");

        if ("payment.updated".equals(action)) {
            Map<String, Object> data = (Map<String, Object>) notification.get("data");
            Long paymentId = Long.parseLong((String) data.get("id"));

            try {
                com.mercadopago.resources.payment.Payment mpPayment = mercadoPagoGateway.getPaymentDetails(paymentId);

                Payment localPayment = paymentRepository.findByMercadoPagoPaymentId(paymentId)
                        .orElseThrow(() -> new RuntimeException("Payment not found for Mercado Pago ID: " + paymentId));

                switch (mpPayment.getStatus()) {
                    case "approved" -> localPayment.setStatus(PaymentStatus.APPROVED);
                    case "cancelled", "rejected" -> localPayment.setStatus(PaymentStatus.CANCELLED);
                    case "refunded" -> localPayment.setStatus(PaymentStatus.REFUNDED);
                    case "pending" -> localPayment.setStatus(PaymentStatus.PENDING);
                    default -> log.warn("Payment status not handled: {}", mpPayment.getStatus());
                }

                paymentRepository.save(localPayment);

            } catch (MPException | MPApiException e) {
                log.error("Error while processing Mercado Pago webhook: ", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.ok().build();
    }

}

