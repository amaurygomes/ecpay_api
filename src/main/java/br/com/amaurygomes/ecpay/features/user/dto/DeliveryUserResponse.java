package br.com.amaurygomes.ecpay.features.user.dto;

import br.com.amaurygomes.ecpay.features.user.entity.PlatformStatus;

import java.util.UUID;

public record DeliveryUserResponse(
    UUID id,
    String name,
    String login,
    Long vtr,
    PlatformStatus platformStatus,
    boolean isActive
) {
}
