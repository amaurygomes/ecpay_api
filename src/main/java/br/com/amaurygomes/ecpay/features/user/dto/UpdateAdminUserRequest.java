package br.com.amaurygomes.ecpay.features.user.dto;

import br.com.amaurygomes.ecpay.features.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateAdminUserRequest(
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    @NotBlank
    String name,
    @Email
    String login,
    @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
    @NotBlank
    String password,
    Role role
) {
}
