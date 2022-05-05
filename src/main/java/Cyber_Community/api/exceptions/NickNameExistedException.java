package Cyber_Community.api.exceptions;


public class NickNameExistedException extends RuntimeException{
    public NickNameExistedException(){

    }

    public NickNameExistedException(String message){
        super(message);

    }
}
