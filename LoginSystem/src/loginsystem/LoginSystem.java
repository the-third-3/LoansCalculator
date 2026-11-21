import java.sql.*;
import java.util.Scanner;

public class LoginSystem {


    // 🔹 CHANGE THESE TO MATCH YOUR DATABASE
    static final String URL = "jdbc:mysql://localhost:3306/ongili";
    static final String USER = "root";
    static final String PASSWORD = ""; // your mysql password

    public static void main(String[] args) {

        createTables();      // create users table
        createDefaultAdmin(); // insert admin if not exists

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HOTEL SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    loginUser(sc);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 🔹 CREATE USERS TABLE
    public static void createTables() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement()) {

            st.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) UNIQUE NOT NULL," +
                    "password VARCHAR(100) NOT NULL," +
                    "role VARCHAR(20) NOT NULL)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 CREATE DEFAULT ADMIN
    public static void createDefaultAdmin() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String checkAdmin = "SELECT * FROM users WHERE username='admin'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(checkAdmin);

            if (!rs.next()) {
                String insertAdmin = "INSERT INTO users (username,password,role) VALUES ('admin','admin123','Admin')";
                st.executeUpdate(insertAdmin);
                System.out.println("Default admin created (username: admin, password: admin123)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 REGISTER USER
    public static void registerUser(Scanner sc) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "INSERT INTO users (username,password,role) VALUES (?,?,'User')";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            pst.executeUpdate();
            System.out.println("Registration successful!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 LOGIN
    public static void loginUser(Scanner sc) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                if (role.equalsIgnoreCase("Admin")) {
                    adminDashboard(sc);
                } else {
                    userDashboard(sc);
                }

            } else {
                System.out.println("Invalid username or password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 ADMIN DASHBOARD
    public static void adminDashboard(Scanner sc) {
        while (true) {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. View Users");
            System.out.println("2. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                viewUsers();
            } else {
                break;
            }
        }
    }

    // 🔹 VIEW ALL USERS (ADMIN)
    public static void viewUsers() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT id, username, role FROM users");

            System.out.println("\nID | Username | Role");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("username") + " | " +
                        rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 USER DASHBOARD
    public static void userDashboard(Scanner sc) {
        System.out.println("\n--- USER DASHBOARD ---");
        System.out.println("Welcome! You are logged in as a normal user.");
    }
}