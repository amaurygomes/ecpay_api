package br.com.amaurygomes.ecpay.features.user.dto;

import jakarta.validation.constraints.*;

public record CreateDeliveryUserRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @NotNull(message = "VTR is required")
    @Min(value = 1, message = "VTR must be greater than 0")
    @Max(value = 9999, message = "VTR must be less than 1000")
    Long vtr,
    @Email
    String login,
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
    String password
) {
}
