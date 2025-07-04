package models;

import Products.Product;
import exceptions.EmptyCartException;
import exceptions.ExpiredException;
import exceptions.OutOfStockException;
import interfaces.Expirable;
import interfaces.Shippable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    ArrayList<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<CartItem>();
    }

    public ArrayList<CartItem> getCartItems() {
        return this.items;
    }

    public void addProduct(Product p, Integer q) throws Exception {
        // whether the quantity is more than the available stock
        if (q > p.getStock()) throw new OutOfStockException(p.getName());

        for (CartItem i : this.items) {
            if (i.getProduct().getName() == p.getName()) {
                // if quantity is more than the available stock
                if (q + i.getQuantity() > i.getProduct().getStock()) {
                    throw new OutOfStockException(i.getProduct().getName());
                }

                i.addProduct(q);
                return;
            }
        }

        this.items.add(new CartItem(p, q));
    }

    public double calculateSubtotal() throws EmptyCartException {
        double subtotal = 0.0;

        if (items.isEmpty()) {
            throw new EmptyCartException();
        }

        for (CartItem i : this.items) {
            subtotal += i.getProduct().getPrice() * i.getQuantity();
        }

        return subtotal;
    }

    public double calculateShippingFees() throws EmptyCartException {
        double shippingFees = 0.0;

        if (items.isEmpty()) {
            throw new EmptyCartException();
        }

        for (CartItem i : this.items) {
            if (i.getProduct() instanceof Shippable) {
                Double weight = ((Shippable) i.getProduct()).getWeight();
                Integer amount = i.getQuantity();

                shippingFees += weight * amount / 2.0; // shipping fees assumed formula
            }
        }
        return shippingFees;
    }

//    public void checkout(Customer customer) throws Exception {
//        if (this.items.isEmpty()) {
//            throw new EmptyCartException();
//        }
//
//        double subtotal;
//        double shippingFees;
//        try {
//            subtotal = this.calculateSubtotal();
//            shippingFees = this.calculateShippingFees();
//        } catch (EmptyCartException e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//
//        if (customer.getBalance() < subtotal + shippingFees) {
//            throw new InsufficientBalanceException(customer.getBalance(), subtotal + shippingFees);
//        }
//
//        ArrayList<Shippable> shippableItems = new ArrayList<Shippable>();
//        Map<Shippable, Integer> quantities = new HashMap<>(Map.of());
//
//        for (CartItem i : this.items) {
//            Product product = i.getProduct();
//
//            if (product.getStock() < i.getQuantity()) {
//                throw new OutOfStockException(product.getName());
//            }
//
//            if (product instanceof Expirable) {
//                if (((Expirable) product).isExpired()) {
//                    throw new ExpiredException(product.getName());
//                }
//            }
//
//            if (product instanceof Shippable) {
//                shippableItems.add((Shippable) product);
//                quantities.put((Shippable) product, i.getQuantity());
//            }
//        }
//
//        new ShippingService().processShipments(shippableItems, quantities);
//
//        this.printCheckout(customer.getBalance(), subtotal, shippingFees);
//
//        customer.withdraw(subtotal + shippingFees);
//
//        this.emptyCart(); // empty the cart after checkout
//    }

    public void emptyCart() {

        for (CartItem i : this.items) {
            i.getProduct().withdraw(i.getQuantity());
        }

        this.items.clear();
    }

    public void verifyCart() throws Exception {
        for (CartItem i : this.items) {
            Product product = i.getProduct();

            if (product.getStock() < i.getQuantity()) {
                throw new OutOfStockException(product.getName());
            }

            if (product instanceof Expirable) {
                if (((Expirable) product).isExpired()) {
                    throw new ExpiredException(product.getName());
                }
            }
        }
    }

    public ArrayList<Shippable> getShippableItems() throws Exception {
        ArrayList<Shippable> shippableItems = new ArrayList<Shippable>();

        for (CartItem i : this.items) {
            Product product = i.getProduct();

            if (product instanceof Shippable) {
                shippableItems.add((Shippable) product);
            }
        }
        return shippableItems;
    }

    public Map<Shippable, Integer> getShippableQuantities() {
        Map<Shippable, Integer> quantities = new HashMap<>(Map.of());

        for (CartItem i : this.items) {
            Product product = i.getProduct();

            if (product instanceof Shippable) {
                quantities.put((Shippable) product, i.getQuantity());
            }
        }
        return quantities;
    }

    public void printCheckout(double balance, double subtotal, double shippingFees) {
        System.out.println("#### Checkout receipt ####");
        for (CartItem i : this.items) {
            System.out.println(i.getQuantity() + "x " + i.getProduct().getName() + "\t" + i.getProduct().getPrice());
        }

        System.out.println("-------------------");
        System.out.println("Subtotal: \t" + subtotal);
        System.out.println("Shipping: \t" + shippingFees);
        System.out.println("Amount: \t" + (subtotal + shippingFees));
        System.out.println("Paid: " + balance + "\tRemaining: " + (balance - subtotal - shippingFees) + "\n");
    }
}
