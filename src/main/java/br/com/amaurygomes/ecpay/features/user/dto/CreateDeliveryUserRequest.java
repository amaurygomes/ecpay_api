package br.com.amaurygomes.ecpay.features.user.dto;

import jakarta.validation.constraints.*;

public record CreateDeliveryUserRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @NotNull(message = "VTR is required")
    @Size(min = 3, message = "VTR must be at least 3")
    Long vtr,
    @NotBlank(message = "Login is required")
    @Pattern(regexp = "\\d{11}", message = "Login must be a valid CPF")
    String login,
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
    String password
) {
}
