package br.com.amaurygomes.ecpay.features.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateSettingsRequest(
        @NotBlank(message = "The settingKey cannot be empty")
        @Pattern(regexp = "^[A-Z_]+$" , message = "The settingKey must contain only uppercase letters and underscores")
        @JsonProperty("setting_key")
        String settingKey,
        @NotBlank(message = "The settingValue cannot be empty")
        @JsonProperty("setting_value")
        String settingValue,
        String description
) {
}