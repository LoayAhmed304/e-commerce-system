package exceptions;

public class OutOfStockException extends CheckoutException {
    public OutOfStockException(String productName){
        super("Insufficient stock for the product " + productName + ".");
    }
}
