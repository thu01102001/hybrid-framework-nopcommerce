package practice;

public class EmployeeDRY {
    private String employeeName;
    private String employeePosition;
    private String employeeType;
    private String employeeSalary;
    private String employeeRate;

    public EmployeeDRY(String employeeName, String employeePosition, String employeeType, String employeeSalary, String employeeRate) {
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeType = employeeType;
        this.employeeSalary = employeeSalary;
        this.employeeRate = employeeRate;
    }

    public void showEmployeeInfor() {
        System.out.println("Name = " +this.employeeName);
        System.out.println("Position = " +this.employeePosition);
        System.out.println("Type = " +this.employeeType);
        if(employeeType.equalsIgnoreCase("Fulltime")) {
            System.out.println("Salary = " +this.employeeSalary);
        } else {
            System.out.println("Rate = " +this.employeeRate);
        }
    }

    public static void main(String[] args) {
        EmployeeDRY kennyGy = new EmployeeDRY("Kenny Gy", "Developer", "Fulltime", "4000", "");
        kennyGy.showEmployeeInfor();
        EmployeeDRY thu = new EmployeeDRY("Thu", "QA", "Partime", "100", "20");
        thu.showEmployeeInfor();
    }
}
