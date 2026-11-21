package inventory.system;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    private String currentUser;
    private String userRole;

    public Dashboard(String user, String role) {
        this.currentUser = user;
        this.userRole = role;

        setTitle("Inventory & Sales Management System - " + role + ": " + user);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(33, 37, 41));
        header.setPreferredSize(new Dimension(1000, 60));
        JLabel title = new JLabel("Digital Inventory & Sales System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabbedPane.addTab("Dashboard", new HomePanel());
        tabbedPane.addTab("Inventory", new InventoryPanel());
        tabbedPane.addTab("Sales Point", new SalesSetup());
        tabbedPane.addTab("Customers", new CustomerPanel());
        
        if (role.equalsIgnoreCase("Admin")) {
            tabbedPane.addTab("Reports", new ReportsPanel());
        }

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
