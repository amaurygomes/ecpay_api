package br.com.amaurygomes.ecpay.config.mercadopago;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoInitConfig {
    @Value("${mercadopago.access.token}")
    private String accessToken;

    @PostConstruct
    public void init(){
        MercadoPagoConfig.setAccessToken(accessToken);
    }

}
