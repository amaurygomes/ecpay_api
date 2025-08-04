package br.com.amaurygomes.ecpay.features.payment.entity;

import br.com.amaurygomes.ecpay.features.user.entity.DeliveryUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="transaction_id", nullable = false, unique = true)
    private String transactionId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingCycle billingCycle;
    private PaymentStatus status = PaymentStatus.PENDING;
    @Column(nullable = false)
    private Double amount;
    @Column(length = 500)
    private String observation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_user_id", nullable = false)
    private DeliveryUser deliveryUser;
    @Column(name = "mp_payment_id", unique = true)
    private Long mercadoPagoPaymentId;
    @Lob
    @Column(name = "mp_qr_code_base64")
    private String mpQrCodeBase64;
    @Lob
    @Column(name = "mp_qr_code_text")
    private String mpQrCodeText;
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

}
