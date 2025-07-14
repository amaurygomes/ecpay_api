package br.com.amaurygomes.ecpay.features.system.service;

import br.com.amaurygomes.ecpay.exception.SettingKeyAlreadyExistsException;
import br.com.amaurygomes.ecpay.exception.SettingKeyNotFoundException;
import br.com.amaurygomes.ecpay.features.system.dto.CreateSettingsRequest;
import br.com.amaurygomes.ecpay.features.system.dto.SettingsResponse;
import br.com.amaurygomes.ecpay.features.system.dto.UpdateSettingsRequest;
import br.com.amaurygomes.ecpay.features.system.entity.Settings;
import br.com.amaurygomes.ecpay.features.system.repository.SettingsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class SettingsService {
    private final SettingsRepository settingsRepository;

    public SettingsResponse create(CreateSettingsRequest request){
        if(settingsRepository.findBySettingKey(request.settingKey()).isPresent()){
            throw new SettingKeyAlreadyExistsException();
        }
        Settings settings = Settings.builder()
                .settingKey(request.settingKey())
                .settingValue(request.settingValue())
                .description(request.description())
                .build();
        return SettingsResponse.fromEntity(settingsRepository.save(settings));
    }

    public SettingsResponse update(String key, UpdateSettingsRequest request){
        Settings settings = settingsRepository.findBySettingKey(key).orElseThrow(() -> new SettingKeyNotFoundException());
        if(hasText(request.settingValue())){
            settings.setSettingValue(request.settingValue());
        }
        if(hasText(request.description())){
            settings.setDescription(request.description());
        }
        return SettingsResponse.fromEntity(settingsRepository.save(settings));
    }

    public SettingsResponse getSetting(String key){
        Settings settings = settingsRepository.findBySettingKey(key).orElseThrow(() -> new SettingKeyNotFoundException());
        return SettingsResponse.fromEntity(settings);
    }

    public void deleteSetting(String key){
        settingsRepository.findBySettingKey(key).orElseThrow(() -> new SettingKeyNotFoundException());
        settingsRepository.deleteBySettingKey(key);
    }


}
