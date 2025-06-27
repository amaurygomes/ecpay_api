package br.com.amaurygomes.ecpay.user.dto;

import br.com.amaurygomes.ecpay.user.entity.Role;
import jakarta.validation.constraints.*;

public record CreateDeliveryUserRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @NotNull(message = "VTR is required")
    @Min(1)@Max(5)
    Long vtr,
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    String login,
    @NotBlank(message = "Password is required")
    @Min(8)
    String password
) {
}
