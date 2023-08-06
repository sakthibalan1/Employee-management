public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Parameterized constructor to set all attributes
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // No-argument constructor (default constructor)
    public Employee() {
        // This constructor is required for creating default instances
    }

    // Getter and Setter methods for the attributes
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary;
    }
}
