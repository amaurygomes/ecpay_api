package br.com.amaurygomes.ecpay.features.payment.service;

import br.com.amaurygomes.ecpay.features.payment.dto.AllPaymentResponse;
import br.com.amaurygomes.ecpay.features.payment.dto.PaymentResponse;
import br.com.amaurygomes.ecpay.features.payment.dto.UpdatePaymentRequest;
import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentStatus;
import br.com.amaurygomes.ecpay.features.payment.repository.PaymentRepository;
import br.com.amaurygomes.ecpay.features.user.entity.DeliveryUser;
import br.com.amaurygomes.ecpay.features.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void savePayment(Payment payment, UUID userId) {
        paymentRepository.findByTransactionId(payment.getTransactionId()).orElseThrow((() -> new RuntimeException("Payment already exists")));
        DeliveryUser deliveryUser = (DeliveryUser) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Delivery User not found"));
        payment.setDeliveryUser(deliveryUser);
        payment.setStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);
    }

    public PaymentResponse updatePayment(String transactionId, UpdatePaymentRequest updatePaymentRequest) {
        Payment payment = paymentRepository.findByTransactionId(transactionId).orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(updatePaymentRequest.status());
        return PaymentResponse.fromEntity(paymentRepository.save(payment));
    }


    public PaymentResponse getPaymentByTransactionId(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId).orElseThrow(() -> new RuntimeException("Payment not found"));
        return PaymentResponse.fromEntity(payment);
    }

    public Page<PaymentResponse> getDeliveryUserPaymentHistory(UUID userId, Pageable pageable) {
        return paymentRepository.findByDeliveryUserId(userId, pageable).map(PaymentResponse::fromEntity);
    }

    public Page<AllPaymentResponse> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(AllPaymentResponse::fromEntity);
    }
}
