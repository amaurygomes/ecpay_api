package br.com.amaurygomes.ecpay.features.user.dto;

import br.com.amaurygomes.ecpay.features.user.entity.PlatformStatus;
import jakarta.validation.constraints.Size;

public record UpdateDeliveryUserRequest(
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    String login,
    @Size(min = 3, message = "VTR must be at least 3")
    Long vtr,
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
    String password,
    PlatformStatus platformStatus,
    boolean isActive
) {
}
