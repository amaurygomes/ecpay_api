package br.com.amaurygomes.ecpay.exception;

public class SettingKeyAlreadyExistsException extends RuntimeException{
    public SettingKeyAlreadyExistsException(){
        super("Setting with this key already exists");
    }

    public SettingKeyAlreadyExistsException(String message){
        super(message);
    }
}
