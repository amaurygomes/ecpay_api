package br.com.amaurygomes.ecpay.gateway.mercadopago;

import br.com.amaurygomes.ecpay.features.payment.entity.BillingCycle;
import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import br.com.amaurygomes.ecpay.features.payment.repository.PaymentRepository;
import br.com.amaurygomes.ecpay.features.user.entity.DeliveryUser;
import br.com.amaurygomes.ecpay.features.user.entity.User;
import br.com.amaurygomes.ecpay.features.user.service.UserService;
import br.com.amaurygomes.ecpay.util.AmountPaymentUtils;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MercadoPagoGeneratePixPayment {
    private final UserService userService;
    private final AmountPaymentUtils amountPaymentUtils;
    private final MercadoPagoGateway mercadoPagoGateway;
    private final PaymentRepository paymentRepository;


    @Transactional
    public MercadoPagoPixPaymentResponse generatePix(UUID userId, BillingCycle billingCycle){
        User user = userService.existsUserById(userId);
        DeliveryUser deliveryUser = (DeliveryUser) user;

        OffsetDateTime currentBillingCucle = OffsetDateTime.now();
        Double paymentAmountValue = amountPaymentUtils.calculatePaymentValue(userId, billingCycle);
        String internaTransactionId = UUID.randomUUID().toString();

        try{
            com.mercadopago.resources.payment.Payment pixPayment = mercadoPagoGateway.createPixPayment(
                    BigDecimal.valueOf(paymentAmountValue),
                    "Payment for " + billingCycle.name() + " billing cycle",
                    internaTransactionId,
                    deliveryUser.getLogin()
            );

            Payment newPayment = Payment.builder()
                    .transactionId(internaTransactionId)
                    .deliveryUser(deliveryUser)
                    .status(PaymentStatus.PENDING)
                    .billingCycle(billingCycle)
                    .mercadoPagoPaymentId(pixPayment.getId())
                    .mpQrCodeBase64(pixPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64())
                    .mpQrCodeText(pixPayment.getPointOfInteraction().getTransactionData().getQrCode())
                    .creationDate(currentBillingCucle.toLocalDateTime())
                    .build();

            paymentRepository.save(newPayment);

            return new MercadoPagoPixPaymentResponse(
                    internaTransactionId,
                    pixPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    pixPayment.getPointOfInteraction().getTransactionData().getQrCode()
            );
        } catch (MPApiException e) {
            log.error("Error while creating Mercado Pago PIX payment: " + e.getApiResponse().getContent());
            throw new RuntimeException("Error while creating Mercado Pago PIX payment" );
        } catch (MPException e) {
            log.error("Error while creating Mercado Pago PIX payment: " + e.getMessage());
            throw new RuntimeException("Error while creating Mercado Pago PIX payment");
        }




    }
}
