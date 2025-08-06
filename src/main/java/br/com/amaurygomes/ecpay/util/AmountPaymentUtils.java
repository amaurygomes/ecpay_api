package br.com.amaurygomes.ecpay.util;

import br.com.amaurygomes.ecpay.features.payment.entity.BillingCycle;
import br.com.amaurygomes.ecpay.features.payment.entity.Payment;
import br.com.amaurygomes.ecpay.features.payment.entity.PaymentValues;
import br.com.amaurygomes.ecpay.features.payment.repository.PaymentAmountRepository;
import br.com.amaurygomes.ecpay.features.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AmountPaymentUtils {
    private final PaymentAmountRepository paymentAmountRepository;
    private final PaymentRepository paymentRepository;

    public Double calculatePaymentValue(UUID userId, BillingCycle billingCycle){
        switch (billingCycle.toString()) {
            case "DAILY":
                return getDailyAmountValue(userId);

            case "WEEKLY":
                return getWeeklyAmountValue(userId);

            case "MONTHLY":
                return getMonthlyAmountValue(userId);


            default:
                throw new RuntimeException("Invalid billing cycle");
        }
    }

    private Double getDailyAmountValue(UUID userId){
        PaymentValues paymentValues = getPaymentValues();

        Double dailyAmount = switch (DayOfWeek.valueOf(LocalDate.now().getDayOfWeek().name())) {
            case MONDAY -> paymentValues.getMondayAmount();
            case TUESDAY -> paymentValues.getTuesdayAmount();
            case WEDNESDAY -> paymentValues.getWednesdayAmount();
            case THURSDAY -> paymentValues.getThursdayAmount();
            case FRIDAY -> paymentValues.getFridayAmount();
            case SATURDAY -> paymentValues.getSaturdayAmount();
            default -> paymentValues.getDefaulDailyAmount();
        };

        double amount;
        if (dailyAmount != null && dailyAmount > 0){
            amount = dailyAmount;
        } else {
            amount = paymentValues.getDefaulDailyAmount();
        }

        if(applyFineAmount(userId)){
            return amount + getPaymentValues().getFineAmount();
        }else{
            return amount;
        }

    }

    private Double getWeeklyAmountValue(UUID userId){
        PaymentValues paymentValues = getPaymentValues();
        if (applyFineAmount(userId)){
            return paymentValues.getDefaultWeeklyAmount() + getPaymentValues().getFineAmount();
        }else{
            return paymentValues.getDefaultWeeklyAmount();
        }
    }

    private Double getMonthlyAmountValue(UUID userId){
        PaymentValues paymentValues = getPaymentValues();
        if (applyFineAmount(userId)){
            return paymentValues.getDefaultMonthlyAmount() + getPaymentValues().getFineAmount();
        }else{
            return paymentValues.getDefaultMonthlyAmount();
        }
    }



    private boolean applyFineAmount(UUID userId) {
        Optional<Payment> lastPaymentOptional = paymentRepository.findTopByDeliveryUserIdOrderByPaymentDateDesc(userId);

        return lastPaymentOptional.map(payment -> {
            if(payment.getPaymentDate() == null){
                return false;
            }

            if (payment.getBillingCycle() == BillingCycle.MONTHLY) {
                return payment.getPaymentDate().toLocalDate().isBefore(LocalDate.now().minusMonths(2));
            } else {
                return payment.getPaymentDate().toLocalDate().isBefore(LocalDate.now().minusMonths(1));
            }
        }).orElse(false);
    }

    private PaymentValues getPaymentValues(){
        Optional<PaymentValues> paymentValues = paymentAmountRepository.findById(1L);
        if (paymentValues.isEmpty()){
            throw new RuntimeException("Payment values not found, please insert the values in the database");
        }
        return paymentValues.get();
    }

}
