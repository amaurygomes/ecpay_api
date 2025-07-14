package br.com.amaurygomes.ecpay.features.system.dto;

import br.com.amaurygomes.ecpay.features.system.entity.Settings;

public record UpdateSettingsRequest(
        String settingValue,
        String description
) {

}
