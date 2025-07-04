package Products;

import interfaces.Expirable;
import interfaces.Shippable;

import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {
    private final double weight;
    private final LocalDate expiryDate;

    public Cheese(String name, double price, Integer stock, double weight, LocalDate expiryDate) {
        super(name, price, stock);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
