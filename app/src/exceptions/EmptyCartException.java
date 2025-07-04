package exceptions;

public class EmptyCartException extends CheckoutException {
    public EmptyCartException(){
        super("Your cart is empty. Can't checkout an empty cart.");
    }
}
