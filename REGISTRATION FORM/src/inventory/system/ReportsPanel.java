package inventory.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class ReportsPanel extends JPanel {

    private JTextArea reportArea;

    public ReportsPanel() {
        setLayout(new BorderLayout());
        
        JButton genBtn = new JButton("Generate Daily Report");
        add(genBtn, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

       
    }

    private void generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DAILY BUSINESS REPORT ===\n\n");

        try (Connection con = DatabaseHelper.getConnection(); Statement st = con.createStatement()) {
            
            // Sales
            ResultSet rs = st.executeQuery("SELECT SUM(amount) FROM transactions WHERE type='Sale'");
            if (rs.next()) {
                sb.append("Total Sales: $").append(rs.getDouble(1)).append("\n");
            }

            // Low Stock
            sb.append("\n[LOW STOCK ALERT]\n");
            rs = st.executeQuery("SELECT name, quantity FROM products WHERE quantity <= min_stock_level");
            while (rs.next()) {
                sb.append("- ").append(rs.getString("name")).append(": ").append(rs.getInt("quantity")).append(" remaining\n");
            }

            // Total Customers
             rs = st.executeQuery("SELECT COUNT(*) FROM customers");
            if (rs.next()) {
                sb.append("\nTotal Customers: ").append(rs.getInt(1)).append("\n");
            }

        } catch (SQLException e) {
            sb.append("Error generating report: ").append(e.getMessage());
        }

        reportArea.setText(sb.toString());
    }
}
