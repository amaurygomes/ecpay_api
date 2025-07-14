package br.com.amaurygomes.ecpay.features.system.controller;

import br.com.amaurygomes.ecpay.features.system.dto.CreateSettingsRequest;
import br.com.amaurygomes.ecpay.features.system.dto.SettingsResponse;
import br.com.amaurygomes.ecpay.features.system.dto.UpdateSettingsRequest;
import br.com.amaurygomes.ecpay.features.system.service.SettingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/settings")
@RequiredArgsConstructor
public class SettingsController {
    private final SettingsService settingsService;

    @PostMapping
    public ResponseEntity<SettingsResponse> create(@RequestBody @Valid CreateSettingsRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(settingsService.create(request));
    }

    @PutMapping("/{key}")
    public ResponseEntity<SettingsResponse> update(@PathVariable String key, @RequestBody @Valid UpdateSettingsRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.update(key, request));

    }

    @GetMapping("/{key}")
    public ResponseEntity<SettingsResponse> getSetting(@PathVariable String key){
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getSetting(key));
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteSetting(@PathVariable String key){
        settingsService.deleteSetting(key);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
