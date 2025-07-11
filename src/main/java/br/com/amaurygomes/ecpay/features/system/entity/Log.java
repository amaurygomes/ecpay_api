package br.com.amaurygomes.ecpay.features.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "log_level", nullable = false)
    private LogLevel logLevel;

    @Column(name = "log_message", nullable = false)
    private String logMessage;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
