import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;

    // Constructor
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    // Method to calculate bonus (base implementation)
    public double calculateBonus() {
        return 0; // Base employees have no bonus by default
    }

    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        if (hourlyRate <= 0 || hoursWorked < 0) {
            throw new IllegalArgumentException("Invalid hourly rate or hours worked.");
        }
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus() {
        return calculateWeeklySalary() * 0.1; // 10% of weekly salary as bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + calculateWeeklySalary() * 52);
    }
}

class SalariedEmployee extends Employee {
    double monthlySalary;

    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        if (monthlySalary <= 0) {
            throw new IllegalArgumentException("Invalid monthly salary.");
        }
        this.monthlySalary = monthlySalary;
    }

    public double calculateWeeklySalary() {
        return monthlySalary / 4;
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05; // 5% of monthly salary as bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + monthlySalary * 12);
    }
}

class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;

    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("Invalid bonus percentage.");
        }
        this.bonusPercentage = bonusPercentage;
    }

    @Override
    public double calculateBonus() {
        double baseBonus = super.calculateBonus();
        return baseBonus + (monthlySalary * 12 * bonusPercentage / 100); // Annual bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bonus Percentage: " + bonusPercentage + "%");
        System.out.println("Total Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + (monthlySalary * 12 + calculateBonus()));
    }
}

public class EmployeePayrollSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>(); // List to hold all employees

        // Input and create HourlyEmployee
        System.out.println("Enter Hourly Employee Details:");
        System.out.print("Employee ID: ");
        int hourlyEmployeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Employee Name: ");
        String hourlyEmployeeName = scanner.nextLine();
        System.out.print("Designation: ");
        String hourlyDesignation = scanner.nextLine();
        System.out.print("Hourly Rate: ");
        double hourlyRate = scanner.nextDouble();
        System.out.print("Hours Worked: ");
        int hoursWorked = scanner.nextInt();
        employees.add(new HourlyEmployee(hourlyEmployeeId, hourlyEmployeeName, hourlyDesignation, hourlyRate, hoursWorked));

        // Input and create SalariedEmployee
        System.out.println("\nEnter Salaried Employee Details:");
        System.out.print("Employee ID: ");
        int salariedEmployeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Employee Name: ");
        String salariedEmployeeName = scanner.nextLine();
        System.out.print("Designation: ");
        String salariedDesignation = scanner.nextLine();
        System.out.print("Monthly Salary: ");
        double monthlySalary = scanner.nextDouble();
        employees.add(new SalariedEmployee(salariedEmployeeId, salariedEmployeeName, salariedDesignation, monthlySalary));

        // Input and create ExecutiveEmployee
        System.out.println("\nEnter Executive Employee Details:");
        System.out.print("Employee ID: ");
        int executiveEmployeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Employee Name: ");
        String executiveEmployeeName = scanner.nextLine();
        System.out.print("Designation: ");
        String executiveDesignation = scanner.nextLine();
        System.out.print("Monthly Salary: ");
        double executiveMonthlySalary = scanner.nextDouble();
        System.out.print("Bonus Percentage: ");
        double bonusPercentage = scanner.nextDouble();
        employees.add(new ExecutiveEmployee(executiveEmployeeId, executiveEmployeeName, executiveDesignation, executiveMonthlySalary, bonusPercentage));

        // Display details of all employees
        System.out.println("\nEmployee Details:");
        for (Employee employee : employees) {
            employee.displayInfo();
            System.out.println(); // Print an empty line for better readability
        }

        // Calculate and display total payroll
        double totalPayroll = 0;
        for (Employee employee : employees) {
            if (employee instanceof HourlyEmployee) {
                totalPayroll += ((HourlyEmployee) employee).calculateWeeklySalary();
            } else if (employee instanceof SalariedEmployee) {
                totalPayroll += ((SalariedEmployee) employee).calculateWeeklySalary();
            } else if (employee instanceof ExecutiveEmployee) {
                totalPayroll += ((ExecutiveEmployee) employee).calculateWeeklySalary();
            }
        }

        System.out.println("Total Payroll: " + totalPayroll);
        scanner.close(); // Close scanner resource
    }
}