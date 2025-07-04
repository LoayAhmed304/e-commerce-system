package Products;

public abstract class Product {
    protected String name;
    protected double price;
    protected Integer stock;

    public Product(String name, double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void withdraw(Integer q) {
        this.stock -= q;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Integer getStock() {
        return this.stock;
    }
}
