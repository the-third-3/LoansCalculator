package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InventoryPanel extends JPanel {
    
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, categoryField, qtyField, priceField, minField, idField;

    public InventoryPanel() {
        setLayout(new BorderLayout());

        // --- Inputs Form ---
        JPanel inputPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Manage Products"));

        nameField = new JTextField();
        categoryField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();
        minField = new JTextField();
        idField = new JTextField(); idField.setEditable(false); // Read-only ID for updates

        inputPanel.add(new JLabel("ID:")); inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:")); inputPanel.add(nameField);
        inputPanel.add(new JLabel("Category:")); inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Quantity:")); inputPanel.add(qtyField);
        inputPanel.add(new JLabel("Price:")); inputPanel.add(priceField);
        inputPanel.add(new JLabel("Min Stock:")); inputPanel.add(minField);

        // --- Buttons ---
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add Product");
        JButton updateBtn = new JButton("Update Selected");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton refreshBtn = new JButton("Refresh List");

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(refreshBtn);

        add(inputPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(btnPanel, BorderLayout.NORTH);

        // --- Table ---
        model = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Qty", "Price", "Min Stock"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        centerPanel.add(scroll, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // --- Actions ---
        addBtn.addActionListener(e -> addProduct());
        refreshBtn.addActionListener(e -> loadData());
        deleteBtn.addActionListener(e -> deleteProduct());
        updateBtn.addActionListener(e -> updateProduct());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                categoryField.setText(model.getValueAt(row, 2).toString());
                qtyField.setText(model.getValueAt(row, 3).toString());
                priceField.setText(model.getValueAt(row, 4).toString());
                minField.setText(model.getValueAt(row, 5).toString());
            }
        });

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try (Connection con = DatabaseHelper.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM products")) {
            
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getInt("min_stock_level")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProduct() {
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "INSERT INTO products(name, category, quantity, price, min_stock_level) VALUES(?,?,?,?,?)")) {
            
            pst.setString(1, nameField.getText());
            pst.setString(2, categoryField.getText());
            pst.setInt(3, Integer.parseInt(qtyField.getText()));
            pst.setDouble(4, Double.parseDouble(priceField.getText()));
            pst.setInt(5, Integer.parseInt(minField.getText()));
            
            pst.executeUpdate();
            loadData();
            clearFields();
            JOptionPane.showMessageDialog(this, "Product Added!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateProduct() {
         try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "UPDATE products SET name=?, category=?, quantity=?, price=?, min_stock_level=? WHERE id=?")) {
            
            pst.setString(1, nameField.getText());
            pst.setString(2, categoryField.getText());
            pst.setInt(3, Integer.parseInt(qtyField.getText()));
            pst.setDouble(4, Double.parseDouble(priceField.getText()));
            pst.setInt(5, Integer.parseInt(minField.getText()));
            pst.setInt(6, Integer.parseInt(idField.getText()));
            
            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Product Updated!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteProduct() {
         try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement pst = con.prepareStatement("DELETE FROM products WHERE id=?")) {
            
            pst.setInt(1, Integer.parseInt(idField.getText()));
            pst.executeUpdate();
            loadData();
            clearFields();
            JOptionPane.showMessageDialog(this, "Product Deleted!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        idField.setText(""); nameField.setText("");
        categoryField.setText(""); qtyField.setText("");
        priceField.setText(""); minField.setText("");
    }
}
