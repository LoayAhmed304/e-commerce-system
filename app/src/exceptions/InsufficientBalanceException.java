package exceptions;

public class InsufficientBalanceException extends CheckoutException{
    public InsufficientBalanceException(double balance, double cost){
        super("Insufficient balance. Your balance: " + balance + " receipt amount: " + cost);
    }
}
