package br.com.amaurygomes.ecpay.features.payment.repository;

import br.com.amaurygomes.ecpay.features.payment.entity.Payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByTransactionId(String transactionId);
    Page<Payment> findByDeliveryUserId(UUID deliveryUserId, Pageable pageable);
    Optional<Payment> findTopByDeliveryUserIdOrderByPaymentDateDesc(UUID deliveryUserId);
}
