import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Employee> employees;
    private Scanner scanner;
    private String fileName;

    public Main(String fileName) {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.fileName = fileName;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveToFile();
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = findEmployeeById(id);
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " has been removed.");
            saveToFile();
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void updateSalary(int id, double newSalary) {
        Employee employeeToUpdate = findEmployeeById(id);
        if (employeeToUpdate != null) {
            employeeToUpdate.setSalary(newSalary);
            System.out.println("Salary for Employee with ID " + id + " has been updated.");
            saveToFile();
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + ","
                        + employee.getDepartment() + "," + employee.getSalary());
                writer.newLine();
            }
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + fileName);
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        employees.clear();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String department = data[2];
                    double salary = Double.parseDouble(data[3]);
                    Employee employee = new Employee(id, name, department, salary);
                    employees.add(employee);
                }
            }
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + fileName);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main ems = new Main("employees.txt");
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Load data from file (if available)
        ems.loadFromFile();

        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee by ID");
            System.out.println("3. Update Salary by ID");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the non-integer input to avoid an infinite loop
                continue; // Continue the loop to ask for a valid input again
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    Employee newEmployee = new Employee(id, name, department, salary);
                    ems.addEmployee(newEmployee);
                    System.out.println("Employee added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Employee ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    ems.removeEmployee(idToDelete);
                    break;
                case 3:
                    System.out.print("Enter Employee ID to update salary: ");
                    int idToUpdateSalary = scanner.nextInt();
                    System.out.print("Enter new Salary: ");
                    double newSalary = scanner.nextDouble();
                    ems.updateSalary(idToUpdateSalary, newSalary);
                    break;
                case 4:
                    System.out.print("Enter Employee ID to search: ");
                    int idToSearch = scanner.nextInt();
                    Employee foundEmployee = ems.findEmployeeById(idToSearch);
                    if (foundEmployee != null) {
                        System.out.println("Found Employee: " + foundEmployee);
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;
                case 5:
                    List<Employee> allEmployees = ems.getAllEmployees();
                    System.out.println("All Employees:");
                    for (Employee employee : allEmployees) {
                        System.out.println(employee);
                    }
                    break;
                case 6:
                    // Save data to file before exiting
                    ems.saveToFile();
                    System.out.println("Exiting the Employee Management System...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
