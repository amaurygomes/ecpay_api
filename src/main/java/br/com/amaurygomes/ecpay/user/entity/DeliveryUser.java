package br.com.amaurygomes.ecpay.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_users")
public class DeliveryUser extends User{
    @Column(nullable = false)
    private Long vtr;
    private PlatformStatus platformStatus;
}
