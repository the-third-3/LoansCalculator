package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {
        setTitle("Inventory System Login");
        setLayout(null); // Set layout to null as requested
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        JLabel title = new JLabel("System Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 30); // Absolute positioning
        add(title);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 30);
        add(userLabel);

        userField = new JTextField(15);
        userField.setBounds(150, 80, 200, 30);
        add(userField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 130, 100, 30);
        add(passLabel);

        passField = new JPasswordField(15);
        passField.setBounds(150, 130, 200, 30);
        add(passField);

        // Button
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBounds(150, 190, 100, 40);
        add(loginBtn);

        loginBtn.addActionListener(e -> authenticate());

        setSize(450, 300); // Fixed size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void authenticate() {
        String u = userField.getText();
        String p = String.valueOf(passField.getPassword());

        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT role FROM hotel_users WHERE username=? AND password=?")) {
            
            pst.setString(1, u);
            pst.setString(2, p);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                dispose(); // Close login
                new Dashboard(u, role); // Open Dashboard
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }
}
