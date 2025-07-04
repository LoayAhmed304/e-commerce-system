import Products.Biscuit;
import Products.Cheese;
import Products.TV;
import models.Customer;
import models.ShoppingCart;
import services.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Customer loay = new Customer(5000.0);

        // 1. Empty cart case
        System.out.println("\tTEST CASE 1\t");
        try {
            ShoppingCart cart = new ShoppingCart();
            new CheckoutService().checkout(cart, loay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tEND\t\n\n");

        // 2. Normal testcase, with expirable product, shippable product, and expirable & shippable product
        System.out.println("\tTEST CASE 2\t");
        try {
            Cheese cheese = new Cheese("Cheese", 20, 10, 5.0, LocalDate.now().plusDays(2));
            TV tv = new TV("TV", 2000.0, 3, 10.0);
            Biscuit biscuit = new Biscuit("Biscuit", 10, 30, LocalDate.now().plusDays(1));

            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(cheese, 3);
            cart.addProduct(tv, 1);
            cart.addProduct(biscuit, 5);
            new CheckoutService().checkout(cart, loay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tEND\t\n\n");

        // Current customer balance: 2877.5
        loay.deposit(1000); // deposit extra 1000

        // 3. Expired product case.
        System.out.println("\tTEST CASE 3\t");

        try {
            Cheese cheese = new Cheese("Cheese", 20, 10, 5.0, LocalDate.now().minusDays(1));
            TV tv = new TV("TV", 2000.0, 3, 10.0);
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(tv, 1);
            cart.addProduct(cheese, 3);
            new CheckoutService().checkout(cart, loay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tEND\t\n\n");

        loay.withdraw(2000.0); // withdraw 2000 to make the balance 1877

        // 4. Insufficient balance case
        System.out.println("\tTEST CASE 4\t");
        try {
            TV tv = new TV("TV", 2000.0, 3, 10.0);
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(tv, 1);
            new CheckoutService().checkout(cart, loay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tEND\t\n\n");

        // Deposit some money
        loay.deposit(10000);

        // 5. Out of stock case.
        System.out.println("\tTEST CASE 5\t");
        try {
            TV tv = new TV("TV", 2000.0, 2, 10.0);
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(tv, 2);
            new CheckoutService().checkout(cart, loay);
            cart.addProduct(tv, 1);
            new CheckoutService().checkout(cart, loay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tEND\t\n\n");
    }
}