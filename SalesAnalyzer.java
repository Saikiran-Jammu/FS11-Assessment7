import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class SalesAnalyzer {
    public static void main(String[] args) {
        // Sample list of product sales
        List<Product> sales = List.of(
                new Product("Product1", 25.0),
                new Product("Product2", 75.0),
                new Product("Product3", 150.0),
                new Product("Product4", 35.0),
                new Product("Product5", 125.0)
        );

        // Initialize a map to store sales within price ranges
        Map<String, Integer> salesCountByRange = new HashMap<>();
        Map<String, Double> revenueByRange = new HashMap<>();

        // Define price ranges
        double[] priceRanges = {0, 50, 100, 200, Double.MAX_VALUE};

        // Initialize counts and revenues for each range
        for (double range : priceRanges) {
            salesCountByRange.put(String.format("$%.2f-$%.2f", range, getNextRange(range)), 0);
            revenueByRange.put(String.format("$%.2f-$%.2f", range, getNextRange(range)), 0.0);
        }

        // Iterate through sales and update counts and revenues
        for (Product product : sales) {
            double price = product.getPrice();
            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i + 1]) {
                    String rangeKey = String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i + 1]);
                    salesCountByRange.put(rangeKey, salesCountByRange.get(rangeKey) + 1);
                    revenueByRange.put(rangeKey, revenueByRange.get(rangeKey) + price);
                    break;
                }
            }
        }

        // Print results
        System.out.println("Sales count and revenue by price range:");
        for (Map.Entry<String, Integer> entry : salesCountByRange.entrySet()) {
            String range = entry.getKey();
            int count = entry.getValue();
            double revenue = revenueByRange.get(range);
            System.out.printf("%s : Sales Count: %d, Total Revenue: $%.2f\n", range, count, revenue);
        }
    }

    // Helper method to get the next price range
    private static double getNextRange(double currentRange) {
        if (currentRange == Double.MAX_VALUE) {
            return Double.MAX_VALUE;
        }
        return currentRange + 50;
    }
}