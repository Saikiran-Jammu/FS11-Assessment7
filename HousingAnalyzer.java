import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class House {
    private double price;
    private double squareFootage;

    public House(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}

public class HousingAnalyzer {
    public static void main(String[] args) {
        // Sample list of housing prices
        List<House> housingPrices = List.of(
                new House(150000, 1200),
                new House(250000, 1800),
                new House(180000, 1500),
                new House(300000, 2000),
                new House(120000, 1000)
        );

        // Initialize a map to store counts and total square footage for each price range
        Map<String, Integer> houseCountByPriceRange = new HashMap<>();
        Map<String, Double> totalSquareFootageByPriceRange = new HashMap<>();

        // Define price ranges
        double[] priceRanges = {100000, 200000, 300000, 400000, Double.MAX_VALUE};

        // Initialize counts and total square footage for each range
        for (double range : priceRanges) {
            houseCountByPriceRange.put(getRangeLabel(range), 0);
            totalSquareFootageByPriceRange.put(getRangeLabel(range), 0.0);
        }

        // Iterate through housing prices and update counts and total square footage
        for (House house : housingPrices) {
            double price = house.getPrice();
            double squareFootage = house.getSquareFootage();

            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i + 1]) {
                    String rangeKey = getRangeLabel(priceRanges[i]);
                    houseCountByPriceRange.put(rangeKey, houseCountByPriceRange.get(rangeKey) + 1);
                    totalSquareFootageByPriceRange.put(rangeKey, totalSquareFootageByPriceRange.get(rangeKey) + squareFootage);
                    break;
                }
            }
        }

        // Calculate average square footage for each price range and print results
        System.out.println("Number of houses sold and average square footage by price range:");
        for (Map.Entry<String, Integer> entry : houseCountByPriceRange.entrySet()) {
            String range = entry.getKey();
            int houseCount = entry.getValue();
            double totalSquareFootage = totalSquareFootageByPriceRange.get(range);
            double averageSquareFootage = totalSquareFootage / houseCount;
            System.out.printf("%s : Houses Sold: %d, Average Square Footage: %.2f\n", range, houseCount, averageSquareFootage);
        }
    }

    // Helper method to get the label for the price range
    private static String getRangeLabel(double range) {
        if (range == Double.MAX_VALUE) {
            return "Over " + String.valueOf((int) priceRanges[priceRanges.length - 2]);
        } else {
            int lowerBound = (int) range / 1000;
            int upperBound = (int) (range + 99999) / 1000;
            return "$" + lowerBound + "K - $" + upperBound + "K";
        }
    }
}