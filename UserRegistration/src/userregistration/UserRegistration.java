

package UserRegistration;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UserRegistration {

    private JFrame mainFrame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField phoneField;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public UserRegistration() {
        initializeUI();
        connectToDatabase();
    }

    private void initializeUI() {
        mainFrame = new JFrame();
        mainFrame.setLayout(null);
        // Changed background to white for better readability, can be changed back to Color.RED if preferred
        mainFrame.getContentPane().setBackground(Color.RED); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure app closes properly

        // --- Labels ---
        JLabel titleLabel = new JLabel("New User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 27));
        
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        JLabel emailLabel = new JLabel("Email Address");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel phoneLabel = new JLabel("Phone Number");

        // Keep original bounds
        titleLabel.setBounds(230, 0, 370, 70);
        firstNameLabel.setBounds(50, 90, 100, 40);
        lastNameLabel.setBounds(50, 160, 100, 40);
        emailLabel.setBounds(50, 240, 100, 40);
        usernameLabel.setBounds(400, 90, 100, 40);
        passwordLabel.setBounds(400, 160, 100, 40);
        phoneLabel.setBounds(400, 240, 100, 40);

        mainFrame.add(titleLabel);
        mainFrame.add(firstNameLabel);
        mainFrame.add(lastNameLabel);
        mainFrame.add(emailLabel);
        mainFrame.add(usernameLabel);
        mainFrame.add(passwordLabel);
        mainFrame.add(phoneLabel);

        // --- Text Fields ---
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        phoneField = new JTextField(20);

        // Keep original bounds
        firstNameField.setBounds(180, 90, 180, 40);
        lastNameField.setBounds(180, 160, 180, 40);
        emailField.setBounds(180, 240, 180, 40);
        usernameField.setBounds(500, 90, 180, 40);
        passwordField.setBounds(500, 160, 180, 40);
        phoneField.setBounds(500, 240, 180, 40);

        mainFrame.add(firstNameField);
        mainFrame.add(lastNameField);
        mainFrame.add(emailField);
        mainFrame.add(usernameField);
        mainFrame.add(passwordField);
        mainFrame.add(phoneField);

        // --- Buttons ---
        JButton registerButton = new JButton("REGISTER");
        JButton nextButton = new JButton("NEXT");
        JButton deleteButton = new JButton("DELETE");
        JButton previousButton = new JButton("PREVIOUS");
        JButton exitButton = new JButton("EXIT");

        // Keep original bounds
        registerButton.setBounds(50, 380, 100, 40);
        deleteButton.setBounds(180, 380, 100, 40);
        nextButton.setBounds(310, 380, 100, 40);
        previousButton.setBounds(440, 380, 100, 40);
        exitButton.setBounds(570, 380, 100, 40);

        mainFrame.add(registerButton);
        mainFrame.add(nextButton);
        mainFrame.add(deleteButton);
        mainFrame.add(previousButton);
        mainFrame.add(exitButton);

        // --- Action Listeners ---
        registerButton.addActionListener(e -> registerUser());
        deleteButton.addActionListener(e -> deleteUser());
        nextButton.addActionListener(e -> showNextUser());
        previousButton.addActionListener(e -> showPreviousUser());
        exitButton.addActionListener(e -> System.exit(0));

        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ongili", "root", "");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM registration");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, "Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void registerUser() {
        try {
            String sql = "INSERT INTO registration (first_name, second_name, email, username, password, phone) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, firstNameField.getText().trim());
                pst.setString(2, lastNameField.getText().trim());
                pst.setString(3, emailField.getText().trim());
                pst.setString(4, usernameField.getText().trim());
                pst.setString(5, String.valueOf(passwordField.getPassword()));
                pst.setString(6, phoneField.getText().trim());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Record Inserted");
                
                // Refresh result set to include new record
                resultSet = statement.executeQuery("SELECT * FROM registration");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Error Inserting Record: " + ex.getMessage());
        }
    }

    private void deleteUser() {
        try {
            String sql = "DELETE FROM registration WHERE phone = ?";
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, phoneField.getText().trim());
                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Record Deleted");
                     // Refresh result set
                    resultSet = statement.executeQuery("SELECT * FROM registration");
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "No record found with that phone number.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Error Deleting Record: " + ex.getMessage());
        }
    }

    private void showNextUser() {
        try {
            if (resultSet != null && resultSet.next()) {
                populateFields();
            } else {
                 JOptionPane.showMessageDialog(mainFrame, "End of records or no records found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showPreviousUser() {
        try {
            if (resultSet != null && resultSet.previous()) {
                populateFields();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Start of records.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void populateFields() throws SQLException {
        firstNameField.setText(resultSet.getString("first_name"));
        lastNameField.setText(resultSet.getString("second_name"));
        emailField.setText(resultSet.getString("email"));
        usernameField.setText(resultSet.getString("username"));
        passwordField.setText(resultSet.getString("password"));
        phoneField.setText(resultSet.getString("phone"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationForm::new);
    }
}
















import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {
    
}