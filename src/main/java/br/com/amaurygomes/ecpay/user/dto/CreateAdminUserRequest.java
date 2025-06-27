package br.com.amaurygomes.ecpay.user.dto;

import br.com.amaurygomes.ecpay.user.entity.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAdminUserRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @NotBlank(message = "Login is required")
    @Min(3)
    String login,
    @NotBlank(message = "Password is required")
    @Min(8)
    String password,
    @NotNull(message = "Role is required")
    Role role
) {
}
