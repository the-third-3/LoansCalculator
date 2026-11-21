package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public final class HomePanel extends JPanel {

    private JLabel salesLabel, stockLabel, customersLabel;

    public HomePanel() {
        setLayout(new GridLayout(1, 3, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        salesLabel = createCard("Total Sales Today", Color.decode("#4CAF50")); // Green
        stockLabel = createCard("Low Stock Items", Color.decode("#F44336")); // Red
        customersLabel = createCard("Total Customers", Color.decode("#2196F3")); // Blue

        add(salesLabel);
        add(stockLabel);
        add(customersLabel);

        refreshData();
        
        // Timer to auto-refresh every 30 seconds
        new Timer(30000, e -> refreshData()).start();
    }

    private JLabel createCard(String title, Color color) {
        JLabel label = new JLabel("Loading...");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), title, 
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, 
                new Font("Arial", Font.PLAIN, 16), Color.WHITE));
        return label;
    }

    public void refreshData() {
        try (Connection con = DatabaseHelper.getConnection(); Statement st = con.createStatement()) {
            
            // 1. Sales Today
            ResultSet rs = st.executeQuery("SELECT SUM(amount) FROM transactions WHERE type='Sale' AND DATE(date) = CURDATE()");
            if (rs.next()) {
                double total = rs.getDouble(1);
                salesLabel.setText("$" + String.format("%.2f", total));
            }

            // 2. Low Stock
            rs = st.executeQuery("SELECT COUNT(*) FROM products WHERE quantity <= min_stock_level");
            if (rs.next()) {
                int count = rs.getInt(1);
                stockLabel.setText(count + " Alerts");
            }

            // 3. Customers
            rs = st.executeQuery("SELECT COUNT(*) FROM customers");
            if (rs.next()) {
                int count = rs.getInt(1);
                customersLabel.setText(count + " Registered");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
