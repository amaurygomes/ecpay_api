package br.com.amaurygomes.ecpay.features.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setting_key", nullable = false, unique = true)
    private String settingKey;

    @Lob
    @Column(name = "setting_value", nullable = false)
    private String settingValue;

    @Column(nullable = false)
    private String description;

}
