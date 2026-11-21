package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private JTextField nameField, phoneField, emailField;

    public CustomerPanel() {

        setLayout(new BorderLayout(10,10));

        // -------- Form Panel --------
        JPanel form = new JPanel(new GridLayout(3,2,5,5));

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        form.add(new JLabel("Name:"));
        form.add(nameField);

        form.add(new JLabel("Phone:"));
        form.add(phoneField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        // -------- Button --------
        JButton addBtn = new JButton("Add Customer");
       

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(form, BorderLayout.CENTER);
        topPanel.add(addBtn, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // -------- Table --------
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Phone", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // prevent editing
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadCustomers();
    }

    // -------- Load Customers --------
    private void loadCustomers() {

        model.setRowCount(0);

        try (Connection con = DatabaseHelper.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM hotel_customers")) {

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                });

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this,
                    "Error loading customers: " + e.getMessage());

        }
    }

    // -------- Add Customer --------
    private void addCustomer() {

        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        // Validation
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields");
            return;
        }

        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "INSERT INTO hotel_customers (name, phone, email) VALUES (?, ?, ?)"
             )) {

            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, email);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Customer Added Successfully");

            // Clear fields
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");

            loadCustomers();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this,
                    "Error adding customer: " + e.getMessage());

        }
    }
}