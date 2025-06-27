package br.com.amaurygomes.ecpay.user.dto;

import br.com.amaurygomes.ecpay.user.entity.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdminUserRequest(
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    String name,
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    String login,
    @Min(8)
    String password,
    Role role
) {
}
