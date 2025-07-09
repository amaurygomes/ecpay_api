package br.com.amaurygomes.ecpay.features.user.dto;

import br.com.amaurygomes.ecpay.features.user.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAdminUserRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    String login,
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
    String password,
    @NotNull(message = "Role is required")
    Role role
) {
}
