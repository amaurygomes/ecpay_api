package br.com.amaurygomes.ecpay.exception;

public class UserAlreadyRegistredException extends RuntimeException{

    public UserAlreadyRegistredException(){
        super("User already registered");
    }

    public UserAlreadyRegistredException(String message){
        super(message);
    }

}
