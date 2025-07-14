package br.com.amaurygomes.ecpay.exception;

public class SettingKeyNotFoundException extends RuntimeException{
    public SettingKeyNotFoundException(){
        super("Setting with this key not found");
    }

    public SettingKeyNotFoundException(String message){
        super(message);
    }

}
