

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegister {

    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/register?serverTimezone=UTC";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegister::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("New User Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 320);
        frame.setLayout(null);

        JLabel l1 = new JLabel("First Name:");
        l1.setBounds(20, 20, 120, 25);
        frame.add(l1);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 20, 200, 25);
        frame.add(firstNameField);

        JLabel l2 = new JLabel("Last Name:");
        l2.setBounds(20, 60, 120, 25);
        frame.add(l2);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 60, 200, 25);
        frame.add(lastNameField);

        JLabel l3 = new JLabel("Email Address:");
        l3.setBounds(20, 100, 120, 25);
        frame.add(l3);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 25);
        frame.add(emailField);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setBounds(400, 20, 120, 25);
        frame.add(userLbl);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(520, 20, 160, 25);
        frame.add(usernameField);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(400, 60, 120, 25);
        frame.add(passLbl);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(520, 60, 160, 25);
        frame.add(passwordField);

        JLabel mb = new JLabel("Mobile Number:");
        mb.setBounds(400, 100, 120, 25);
        frame.add(mb);

        JTextField mobileField = new JTextField();
        mobileField.setBounds(520, 100, 160, 25);
        frame.add(mobileField);

        JButton regBtn = new JButton("Register");
        regBtn.setBounds(20, 190, 120, 30);
        frame.add(regBtn);

        JButton delBtn = new JButton("Delete");
        delBtn.setBounds(150, 190, 120, 30);
        frame.add(delBtn);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(280, 190, 120, 30);
        frame.add(nextBtn);

        JButton prevBtn = new JButton("Previous");
        prevBtn.setBounds(410, 190, 120, 30);
        frame.add(prevBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(540, 190, 120, 30);
        frame.add(exitBtn);

        // Register action: insert into DB
        regBtn.addActionListener(e -> {
            String fname = firstNameField.getText().trim();
            String lname = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String uname = usernameField.getText().trim();
            String pwd = new String(passwordField.getPassword());
            String mobile = mobileField.getText().trim();

            if (fname.isEmpty() || uname.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "First name and username are required.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "JDBC Driver not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertSql = "INSERT INTO user (fname, sname, email, uname, psword, mobilenumber) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = con.prepareStatement(insertSql)) {

                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, email);
                ps.setString(4, uname);
                ps.setString(5, pwd);
                ps.setString(6, mobile);

                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, rows > 0 ? "Registration successful." : "No row inserted.");

                loadAndPrintUsers();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete action
        delBtn.addActionListener(e -> {
            String uname = usernameField.getText().trim();
            if (uname.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter username to delete.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String deleteSql = "DELETE FROM user WHERE uname = ?";

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = con.prepareStatement(deleteSql)) {

                ps.setString(1, uname);
                int rows = ps.executeUpdate();

                JOptionPane.showMessageDialog(frame, rows > 0 ? "User deleted." : "No user found with that username.");
                loadAndPrintUsers();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        prevBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Back to the previous field"));
        nextBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fill this new field"));
        exitBtn.addActionListener(e -> frame.dispose());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initial load
        loadAndPrintUsers();
    }

    private static void loadAndPrintUsers() {
        String query = "SELECT fname, sname, email, uname, psword, mobilenumber FROM user";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e.getMessage());
            return;
        }

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Existing users:");
            while (rs.next()) {
                System.out.printf("%s %s | %s | %s | %s | %s%n",
                        rs.getString("fname"),
                        rs.getString("sname"),
                        rs.getString("email"),
                        rs.getString("uname"),
                        rs.getString("psword"),
                        rs.getString("mobilenumber")
                );
            }

        } catch (SQLException ex) {
            System.err.println("DB error: " + ex.getMessage());
        }
    }
}
