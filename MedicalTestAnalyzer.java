import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MedicalTestResult {
    private String patientName;
    private double resultValue;

    public MedicalTestResult(String patientName, double resultValue) {
        this.patientName = patientName;
        this.resultValue = resultValue;
    }

    public double getResultValue() {
        return resultValue;
    }
}

public class MedicalTestAnalyzer {
    public static void main(String[] args) {
        // Sample list of medical test results
        List<MedicalTestResult> testResults = List.of(
                new MedicalTestResult("Patient1", 90.0),
                new MedicalTestResult("Patient2", 110.0),
                new MedicalTestResult("Patient3", 70.0),
                new MedicalTestResult("Patient4", 130.0),
                new MedicalTestResult("Patient5", 95.0),
                new MedicalTestResult("Patient6", 105.0)
        );

        // Initialize a map to store counts and total value for each range
        Map<String, Integer> patientCountByRange = new HashMap<>();
        Map<String, Double> totalValueByRange = new HashMap<>();

        // Define result ranges
        double[] resultRanges = {0, 100, 120, Double.MAX_VALUE};

        // Initialize counts and total value for each range
        for (double range : resultRanges) {
            patientCountByRange.put(getRangeLabel(range), 0);
            totalValueByRange.put(getRangeLabel(range), 0.0);
        }

        // Iterate through test results and update counts and total value
        for (MedicalTestResult result : testResults) {
            double value = result.getResultValue();
            for (int i = 0; i < resultRanges.length - 1; i++) {
                if (value >= resultRanges[i] && value < resultRanges[i + 1]) {
                    String rangeKey = getRangeLabel(resultRanges[i]);
                    patientCountByRange.put(rangeKey, patientCountByRange.get(rangeKey) + 1);
                    totalValueByRange.put(rangeKey, totalValueByRange.get(rangeKey) + value);
                    break;
                }
            }
        }

        // Calculate average value for each range and print results
        System.out.println("Number of patients and average value by result range:");
        for (Map.Entry<String, Integer> entry : patientCountByRange.entrySet()) {
            String range = entry.getKey();
            int patientCount = entry.getValue();
            double totalValue = totalValueByRange.get(range);
            double averageValue = totalValue / patientCount;
            System.out.printf("%s : Patient Count: %d, Average Value: %.2f\n", range, patientCount, averageValue);
        }
    }

    // Helper method to get the label for the result range
    private static String getRangeLabel(double range) {
        if (range == Double.MAX_VALUE) {
            return "High";
        } else if (range == 0) {
            return "Normal";
        } else {
            return "Borderline";
        }
    }
}