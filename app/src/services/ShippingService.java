package services;

import Products.Product;
import interfaces.Shippable;

import java.util.ArrayList;
import java.util.Map;

public class ShippingService {
    ArrayList<Product> p;

    public void processShipments(ArrayList<Shippable> items, Map<Shippable, Integer> quantities) {
        System.out.println("** Shipment Notice **");
        double totalWeight = 0.0;
        for (Shippable item : items) {
            System.out.println(quantities.getOrDefault(item, 0) + "x " + item.getName() + "\t" + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight: \t" + totalWeight + "\n");
    }

}
