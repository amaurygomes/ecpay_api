package br.com.amaurygomes.ecpay.features.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;

@Entity
@Table(name = "payment_values")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentValues {
    @Id
    private Long id;
    @Column(name ="fine_amount", nullable = false)
    private Double fineAmount = 10.0;
    @Column(name ="default_daily_amount", nullable = false)
    private Double defaulDailyAmount = 10.0;
    @Column(name ="default_weekly_amount", nullable = false)
    private Double defaultWeeklyAmount = 80.0;
    @Column(name ="default_monthly_amount", nullable = false)
    private Double defaultMonthlyAmount = 100.0;
    @Column(name ="monday_amount", nullable = false)
    private Double mondayAmount = 0.0;
    @Column(name ="tuesday_amount", nullable = false)
    private Double tuesdayAmount = 0.0;
    @Column(name ="wednesday_amount", nullable = false)
    private Double wednesdayAmount = 0.0;
    @Column(name ="thursday_amount", nullable = false)
    private Double thursdayAmount = 0.0;
    @Column(name ="friday_amount", nullable = false)
    private Double fridayAmount = 0.0;
    @Column(name ="saturday_amount", nullable = false)
    private Double saturdayAmount = 0.0;
}
