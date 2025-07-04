package Products;

import interfaces.Expirable;

import java.time.LocalDate;

public class Biscuit extends Product implements Expirable {
    private final LocalDate expiryDate;

    public Biscuit(String name, double price, Integer stock, LocalDate expiryDate) {
        super(name, price, stock);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public boolean isExpired() {
        return this.expiryDate.isBefore(LocalDate.now());
    }
}
