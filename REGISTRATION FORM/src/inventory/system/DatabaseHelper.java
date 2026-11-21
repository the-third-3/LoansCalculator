package inventory.system;

import java.sql.*;

public class DatabaseHelper {
    
    // Using the same database connection as the user's previous project
    private static final String URL = "jdbc:mysql://localhost:3306/ongili";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initDatabase() {
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            
            // 1. Users Table
            st.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "username VARCHAR(50) PRIMARY KEY, " +
                    "password VARCHAR(50) NOT NULL, " +
                    "role VARCHAR(20) NOT NULL)"); // Roles: 'Admin', 'User'

            // Create default admin if not exists
            try {
                st.execute("INSERT IGNORE INTO users VALUES ('admin', 'admin123', 'Admin')");
            } catch (SQLException ignored) {}

            // 2. Products Table (Inventory)
            st.execute("CREATE TABLE IF NOT EXISTS products (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "category VARCHAR(50), " +
                    "quantity INT DEFAULT 0, " +
                    "price DOUBLE DEFAULT 0.0, " +
                    "min_stock_level INT DEFAULT 5)");

            // 3. Customers Table
            st.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "phone VARCHAR(20), " +
                    "email VARCHAR(100))");

            // 4. Transactions Table (Sales & Expenses)
            st.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "type VARCHAR(20) NOT NULL, " + // 'Sale' or 'Expense'
                    "amount DOUBLE NOT NULL, " +
                    "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "description VARCHAR(255), " +
                    "user_id VARCHAR(50))"); // Foreign key to users logically

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Database Init Error: " + e.getMessage());
        }
    }
}
