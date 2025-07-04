package models;

import Products.Product;

public class CartItem {
    Product product;
    Integer quantity;

    public CartItem(Product p) {
        this.product = p;
        this.quantity = 1;
    }

    public CartItem(Product p, Integer q) {
        this.product = p;
        this.quantity = q;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void addProduct(Integer q) {
        this.quantity += q;
    }

    @Override
    public String toString() {
        return "Item: " + this.product.toString() + "\nAmount: " + this.getQuantity() + "\n";
    }
}
