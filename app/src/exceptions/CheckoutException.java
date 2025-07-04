package exceptions;

public class CheckoutException extends Exception {
    private final String message;

    CheckoutException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
