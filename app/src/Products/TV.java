package Products;

import interfaces.Shippable;

public class TV extends Product implements Shippable {
    private final double weight;

    public TV(String name, double price, Integer stock, double weight) {
        super(name, price, stock);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
