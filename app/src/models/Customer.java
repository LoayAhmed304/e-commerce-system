package models;

public class Customer {
    private Double balance;

    public Customer(Double balance) {
        this.balance = balance;
    }

    public void withdraw(double value) {
        if (value > this.balance) return; // to prevent negative balance

        this.balance -= value;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void deposit(double value) {
        if (value <= 0) return;

        this.balance += value;
    }
}
