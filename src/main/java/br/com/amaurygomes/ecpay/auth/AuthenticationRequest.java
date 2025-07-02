package br.com.amaurygomes.ecpay.auth;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotBlank(message = "Login is required")
        @Size(min = 3, max =20, message = "Login must contain between 3 and 20 characters")
        String login,
        @NotBlank(message = "Password is required")
        @Min(8)
        String password
) {
}
