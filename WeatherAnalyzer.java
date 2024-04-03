import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WeatherData {
    private double temperature;
    private double humidity;

    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

public class WeatherAnalyzer {
    public static void main(String[] args) {
        // Sample list of weather data
        List<WeatherData> weatherDataList = List.of(
                new WeatherData(5.0, 60.0),
                new WeatherData(15.0, 70.0),
                new WeatherData(25.0, 80.0),
                new WeatherData(2.0, 50.0),
                new WeatherData(-5.0, 40.0),
                new WeatherData(12.0, 65.0)
        );

        // Initialize a map to store counts and total humidity for each temperature range
        Map<String, Integer> daysCountByTemperatureRange = new HashMap<>();
        Map<String, Double> totalHumidityByTemperatureRange = new HashMap<>();

        // Define temperature ranges
        double[] temperatureRanges = {-Double.MAX_VALUE, 0, 10, 20, 30, Double.MAX_VALUE};

        // Initialize counts and total humidity for each range
        for (double range : temperatureRanges) {
            daysCountByTemperatureRange.put(String.format("%.0f°C-%.0f°C", range, getNextRange(range)), 0);
            totalHumidityByTemperatureRange.put(String.format("%.0f°C-%.0f°C", range, getNextRange(range)), 0.0);
        }

        // Iterate through weather data and update counts and total humidity
        for (WeatherData data : weatherDataList) {
            double temperature = data.getTemperature();
            double humidity = data.getHumidity();

            for (int i = 0; i < temperatureRanges.length - 1; i++) {
                if (temperature >= temperatureRanges[i] && temperature < temperatureRanges[i + 1]) {
                    String rangeKey = String.format("%.0f°C-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]);
                    daysCountByTemperatureRange.put(rangeKey, daysCountByTemperatureRange.get(rangeKey) + 1);
                    totalHumidityByTemperatureRange.put(rangeKey, totalHumidityByTemperatureRange.get(rangeKey) + humidity);
                    break;
                }
            }
        }

        // Calculate average humidity for each temperature range and print results
        System.out.println("Number of days and average humidity by temperature range:");
        for (Map.Entry<String, Integer> entry : daysCountByTemperatureRange.entrySet()) {
            String range = entry.getKey();
            int daysCount = entry.getValue();
            double totalHumidity = totalHumidityByTemperatureRange.get(range);
            double averageHumidity = totalHumidity / daysCount;
            System.out.printf("%s : Days Count: %d, Average Humidity: %.2f%%\n", range, daysCount, averageHumidity);
        }
    }

    // Helper method to get the next temperature range
    private static double getNextRange(double currentRange) {
        if (currentRange == Double.MAX_VALUE || currentRange == -Double.MAX_VALUE) {
            return Double.MAX_VALUE;
        }
        return currentRange + 10;
    }
}