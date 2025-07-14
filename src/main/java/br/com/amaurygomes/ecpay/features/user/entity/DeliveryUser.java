package br.com.amaurygomes.ecpay.features.user.entity;

import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "delivery_users")
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class DeliveryUser extends User{
    @Column(nullable = false)
    private Long vtr;
    private PlatformStatus platformStatus = PlatformStatus.ACTIVE;
    @OneToMany(mappedBy = "deliveryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}
