package br.com.amaurygomes.ecpay.features.system.dto;

import br.com.amaurygomes.ecpay.features.system.entity.Settings;
import lombok.Builder;

@Builder
public record SettingsResponse(
        Long id,
        String settingKey,
        String settingValue,
        String description
) {

    public static SettingsResponse fromEntity(Settings settings) {
        return new SettingsResponse(
                settings.getId(),
                settings.getSettingKey(),
                settings.getSettingValue(),
                settings.getDescription()
        );
    }
}
