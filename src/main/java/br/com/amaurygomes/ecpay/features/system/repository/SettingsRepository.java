package br.com.amaurygomes.ecpay.features.system.repository;

import br.com.amaurygomes.ecpay.features.system.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Optional<Settings> findBySettingKey(String key);
    void deleteBySettingKey(String key);
}
