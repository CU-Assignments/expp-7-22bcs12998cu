import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectionExample {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/EmployeeDB";  
        String username = "root";  
        String password = "your_password";  

        
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("EmpID\tName\t\tSalary");

            while (resultSet.next()) {
                int empID = resultSet.getInt("EmpID");
                String name = resultSet.getString("Name");
                double salary = resultSet.getDouble("Salary");

                System.out.printf("%d\t%s\t%.2f\n", empID, name, salary);
            }
        } catch (Exception e) {
         
            e.printStackTrace();
        }
    }
}
