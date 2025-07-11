package br.com.amaurygomes.ecpay.features.system.dto;

import br.com.amaurygomes.ecpay.features.system.entity.Log;

import java.time.LocalDateTime;

public record LogResponse(
        Long id,
        String username,
        String ipAddress,
        String logLevel,
        String logMessage,
        LocalDateTime timestamp
) {

    public static LogResponse fromEntity(Log log){
        return new LogResponse(
                log.getId(),
                log.getUsername(),
                log.getIpAddress(),
                log.getLogLevel().name(),
                log.getLogMessage(),
                log.getTimestamp()
        );
    }
}