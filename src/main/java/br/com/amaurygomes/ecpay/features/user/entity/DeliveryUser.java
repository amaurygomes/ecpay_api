package br.com.amaurygomes.ecpay.features.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
}
