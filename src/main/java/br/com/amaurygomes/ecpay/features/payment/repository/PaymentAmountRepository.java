package br.com.amaurygomes.ecpay.features.payment.repository;

import br.com.amaurygomes.ecpay.features.payment.entity.PaymentValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAmountRepository extends JpaRepository<PaymentValues, Long>{

}
