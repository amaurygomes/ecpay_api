package br.com.amaurygomes.ecpay.user.dto;

import br.com.amaurygomes.ecpay.user.entity.PlatformStatus;

public record UpdateDeliveryUserRequest(
    String name,
    String login,
    Long vtr,
    String password,
    PlatformStatus platformStatus,
    boolean isActive
) {
}
