package br.com.amaurygomes.ecpay.user.dto;

import java.util.UUID;

public record AdminUserResponse(
    UUID id,
    String name,
    String login,
    String role
) {
}
