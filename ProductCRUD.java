import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/ProductDB";
    static final String USER = "root"; 
    static final String PASSWORD = "your_password"; 

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ProductID:");
        int productID = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter Product Name:");
        String productName = sc.nextLine();
        System.out.println("Enter Product Price:");
        double price = sc.nextDouble();
        System.out.println("Enter Product Quantity:");
        int quantity = sc.nextInt();

        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
           
            stmt.setInt(1, productID);
            stmt.setString(2, productName);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product created successfully!");
            } else {
                System.out.println("Failed to create product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readProducts() {
        String query = "SELECT * FROM Product";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("ProductID\tProductName\tPrice\tQuantity");
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");

                System.out.printf("%d\t%s\t%.2f\t%d\n", productID, productName, price, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ProductID to update:");
        int productID = sc.nextInt();
        sc.nextLine();  
        System.out.println("Enter new Product Name:");
        String productName = sc.nextLine();
        System.out.println("Enter new Product Price:");
        double price = sc.nextDouble();
        System.out.println("Enter new Product Quantity:");
        int quantity = sc.nextInt();

        String query = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productName);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ProductID to delete:");
        int productID = sc.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
          
            stmt.setInt(1, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Product");
            System.out.println("2. Read Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    readProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return; 
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
