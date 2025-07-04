package exceptions;

public class ExpiredException extends CheckoutException {
    public ExpiredException(String productName){
        super("You have an expired " + productName + " in your cart.");
    }
}
