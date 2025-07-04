package services;

import exceptions.EmptyCartException;
import exceptions.InsufficientBalanceException;
import interfaces.Shippable;
import models.Customer;
import models.ShoppingCart;

import java.util.ArrayList;
import java.util.Map;

public class CheckoutService {

    public void checkout(ShoppingCart cart, Customer customer) throws Exception {

        double subtotal;
        double shippingFees;
        try {
            subtotal = cart.calculateSubtotal();
            shippingFees = cart.calculateShippingFees();
        } catch (EmptyCartException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (customer.getBalance() < subtotal + shippingFees) {
            throw new InsufficientBalanceException(customer.getBalance(), subtotal + shippingFees);
        }

        try {
            cart.verifyCart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        ArrayList<Shippable> shippableItems = cart.getShippableItems();
        Map<Shippable, Integer> quantities = cart.getShippableQuantities();
        new ShippingService().processShipments(shippableItems, quantities);

        cart.printCheckout(customer.getBalance(), subtotal, shippingFees);

        customer.withdraw(subtotal + shippingFees);

        cart.emptyCart(); // empty the cart after checkout
    }
}
