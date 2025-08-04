package br.com.amaurygomes.ecpay.features.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @Email
        String login,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 20, message = "Password must be at least 8 characters")
        String password
) {
}
