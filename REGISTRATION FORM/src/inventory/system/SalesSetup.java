package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalesSetup extends JPanel {

    private final JComboBox<String> productCombo;
    private final JTextField qtyField, totalField;
    private final JTable transactionTable;
    private final DefaultTableModel model;
    
    public SalesSetup() {
        setLayout(new BorderLayout());

        // --- Sales Form ---
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("New Transaction"));

        productCombo = new JComboBox<>();
        loadProducts(); // Load products into combo
        
        qtyField = new JTextField();
        totalField = new JTextField(); totalField.setEditable(false);
        JButton calculateBtn = new JButton("Calculate Total");
        JButton sellBtn = new JButton("Process Sale");
        
        formPanel.add(new JLabel("Product:")); formPanel.add(productCombo);
        formPanel.add(new JLabel("Quantity:")); formPanel.add(qtyField);
        formPanel.add(new JLabel("Total Price:")); formPanel.add(totalField);
        JButton expenseBtn = new JButton("Record Expense");
        formPanel.add(calculateBtn); formPanel.add(sellBtn);
        formPanel.add(expenseBtn);

        add(formPanel, BorderLayout.NORTH);

        // --- Transaction History ---
        model = new DefaultTableModel(new String[]{"ID", "Type", "Amount", "Date", "Desc"}, 0);
        transactionTable = new JTable(model);
        add(new JScrollPane(transactionTable), BorderLayout.CENTER);
        
        loadTransactions();

        // --- Actions ---
        calculateBtn.addActionListener(e -> calculateTotal());
        sellBtn.addActionListener(e -> processSale());
        expenseBtn.addActionListener(e -> recordExpense());
    }

    private void loadProducts() {
        productCombo.removeAllItems();
        try (Connection con = DatabaseHelper.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT name FROM products")) {
            while (rs.next()) {
                productCombo.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateTotal() {
        String pName = (String) productCombo.getSelectedItem();
        if (pName == null) return;
        
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT price FROM products WHERE name=?")) {
            
            pst.setString(1, pName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble("price");
                int qty = Integer.parseInt(qtyField.getText());
                totalField.setText(String.valueOf(price * qty));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity or Error: " + e.getMessage());
        }
    }

    private void processSale() {
        String pName = (String) productCombo.getSelectedItem();
        if (pName == null || totalField.getText().isEmpty()) return;
        
        int qty = Integer.parseInt(qtyField.getText());
        double total = Double.parseDouble(totalField.getText());

        try (Connection con = DatabaseHelper.getConnection()) {
            con.setAutoCommit(false); // Transaction

            // 1. Check Stock
            PreparedStatement checkStock = con.prepareStatement("SELECT quantity FROM products WHERE name=?");
            checkStock.setString(1, pName);
            ResultSet rs = checkStock.executeQuery();
            if (rs.next()) {
                int currentStock = rs.getInt("quantity");
                if (currentStock < qty) {
                    JOptionPane.showMessageDialog(this, "Insufficient Stock!");
                    return;
                }
            }

            // 2. Deduct Stock
            PreparedStatement deduct = con.prepareStatement("UPDATE products SET quantity = quantity - ? WHERE name=?");
            deduct.setInt(1, qty);
            deduct.setString(2, pName);
            deduct.executeUpdate();

            // 3. Record Transaction
            PreparedStatement record = con.prepareStatement("INSERT INTO transactions (type, amount, description) VALUES ('Sale', ?, ?)");
            record.setDouble(1, total);
            record.setString(2, "Sold " + qty + " x " + pName);
            record.executeUpdate();

            con.commit();
            
            JOptionPane.showMessageDialog(this, "Sale Successful!");
            loadTransactions();
            qtyField.setText("");
            totalField.setText("");
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Transaction Failed: " + e.getMessage());
        }
    }

    private void recordExpense() {
        String desc = JOptionPane.showInputDialog(this, "Enter Expense Description:");
        if (desc == null || desc.trim().isEmpty()) return;
        
        String amountStr = JOptionPane.showInputDialog(this, "Enter Expense Amount:");
        if (amountStr == null) return;
        
        try (Connection con = DatabaseHelper.getConnection()) {
            double amount = Double.parseDouble(amountStr);
            PreparedStatement pst = con.prepareStatement("INSERT INTO transactions (type, amount, description) VALUES ('Expense', ?, ?)");
            pst.setDouble(1, amount);
            pst.setString(2, desc);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Expense Recorded");
            loadTransactions();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Amount");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        model.setRowCount(0);
        try (Connection con = DatabaseHelper.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM transactions ORDER BY date DESC LIMIT 50")) {
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("date"),
                    rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
