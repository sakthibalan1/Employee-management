import java.util.*;

class Employee
{
    String name;
    int age;
    int salary;
    Employee(String name,int age,int salary)
    {
        this.name=name;
        this.age=age;
        this.salary=salary;
    }
    public String print()
    {
        return name + " " + age + " " + salary;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        int chh;
        Collection<Employee>ne=new ArrayList<Employee>();
        do
        {
            System.out.println("Enter 1 to add employee ");
            System.out.println("Enter 2 to view employee");
            System.out.println("Enter 0 to exit system  ");
            System.out.print("Enter your choise:");
            chh=sc.nextInt();
            switch(chh)
            {
                case 1:
                {   System.out.print("Enter how many employee to add:");
                    int num=sc.nextInt();
                    for(int i=0;i<num;i++){
                        System.out.print("Enter Employee name:");
                        String name = sc1.nextLine();
                        System.out.print("Enter Employee age:");
                        int age = sc.nextInt();
                        System.out.print("Enter Employee salary:");
                        int salary = sc.nextInt();

                        ne.add(new Employee(name,age,salary));
                    }
                    break;
                }
                case 2:
                {   System.out.println();
                    System.out.println("-----------------");
                    for(Employee emp: ne)
                    {
                        System.out.println(emp.print());

                    }
                    System.out.println("-----------------");
                    System.out.println();
                    break;
                }
            }
        }
        while(chh!=0);

    }
}
