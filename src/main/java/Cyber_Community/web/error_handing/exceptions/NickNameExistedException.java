package Cyber_Community.web.error_handing.exceptions;


public class NickNameExistedException extends RuntimeException{
    public NickNameExistedException(){

    }

    public NickNameExistedException(String message){
        super(message);

    }
}
