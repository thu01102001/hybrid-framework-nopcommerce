package practice;

public class EmployeeRY {
    public void showFulltimexKennyGyDetail() {
        System.out.println("Name = Kenny Gy");
        System.out.println("Position = Developer");
        System.out.println("Type = Fulltime");
        System.out.println("Salary = 4000");
    }

    public void showPartimeVinnothDetail() {
        System.out.println("Name = Vinnoth");
        System.out.println("Position = QA");
        System.out.println("Type = Partime");
        System.out.println("Rate = 20");
    }

    public static void main(String[] args) {
        EmployeeRY emloyee = new EmployeeRY();
        emloyee.showFulltimexKennyGyDetail();
        emloyee.showPartimeVinnothDetail();
    }
}
