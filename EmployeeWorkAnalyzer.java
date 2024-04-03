import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EmployeeWorkHours {
    private String employeeName;
    private List<Integer> hoursWorkedPerDay;

    public EmployeeWorkHours(String employeeName, List<Integer> hoursWorkedPerDay) {
        this.employeeName = employeeName;
        this.hoursWorkedPerDay = hoursWorkedPerDay;
    }

    public List<Integer> getHoursWorkedPerDay() {
        return hoursWorkedPerDay;
    }
}

public class EmployeeWorkAnalyzer {
    public static void main(String[] args) {
        // Sample list of employee work hours
        List<EmployeeWorkHours> workHoursList = List.of(
                new EmployeeWorkHours("Employee1", List.of(8, 8, 8, 8, 8)),
                new EmployeeWorkHours("Employee2", List.of(9, 9, 8, 8, 8)),
                new EmployeeWorkHours("Employee3", List.of(10, 10, 10, 10, 10)),
                new EmployeeWorkHours("Employee4", List.of(7, 8, 8, 7, 8)),
                new EmployeeWorkHours("Employee5", List.of(8, 8, 8, 6, 8))
        );

        // Initialize counters and total hours for each group
        int moreThan40Count = 0;
        int exactly40Count = 0;
        int lessThan40Count = 0;
        Map<String, Integer> totalHoursByGroup = new HashMap<>();
        Map<String, Integer> daysWorkedByGroup = new HashMap<>();

        // Iterate through work hours and update counts and total hours
        for (EmployeeWorkHours workHours : workHoursList) {
            int totalHours = workHours.getHoursWorkedPerDay().stream().mapToInt(Integer::intValue).sum();
            if (totalHours > 40) {
                moreThan40Count++;
            } else if (totalHours == 40) {
                exactly40Count++;
            } else {
                lessThan40Count++;
            }
            totalHoursByGroup.put("More than 40 hours", totalHoursByGroup.getOrDefault("More than 40 hours", 0) + totalHours);
            daysWorkedByGroup.put("More than 40 hours", daysWorkedByGroup.getOrDefault("More than 40 hours", 0) + 5);
        }

        // Calculate average hours worked per day for each group
        Map<String, Double> averageHoursPerDayByGroup = new HashMap<>();
        for (Map.Entry<String, Integer> entry : totalHoursByGroup.entrySet()) {
            String group = entry.getKey();
            int totalHours = entry.getValue();
            int daysWorked = daysWorkedByGroup.get(group);
            double averageHoursPerDay = (double) totalHours / daysWorked;
            averageHoursPerDayByGroup.put(group, averageHoursPerDay);
        }

        // Print results
        System.out.println("Number of employees and average hours per day by work hour group:");
        System.out.printf("More than 40 hours: Employees: %d, Average Hours per Day: %.2f\n", moreThan40Count, averageHoursPerDayByGroup.get("More than 40 hours"));
        System.out.printf("Exactly 40 hours: Employees: %d, Average Hours per Day: %.2f\n", exactly40Count, 8.0);
        System.out.printf("Less than 40 hours: Employees: %d, Average Hours per Day: %.2f\n", lessThan40Count, averageHoursPerDayByGroup.get("More than 40 hours"));
    }
}