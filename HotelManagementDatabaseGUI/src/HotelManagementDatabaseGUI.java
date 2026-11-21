
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.*;
import java.awt.event.ActionListener;

public class HotelManagementDatabaseGUI {

    static DefaultTableModel tableModel;
    static JTable table;
    static Map<String, Integer> guests = new HashMap<>();
    static boolean darkMode = false;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Melg Hotel Management");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // --- Background Image ---
        JLabel background = new JLabel(new ImageIcon("melg.png")); // Replace with your image
        background.setLayout(new BorderLayout());
        frame.setContentPane(background);

        // --- Table ---
        String[] columns = { "Guest Name", "Current Bill (KSH)" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : new Color(220, 220, 220));
                }
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        background.add(scroll, BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        buttonPanel.setOpaque(false); // Transparent

        // Buttons with colors
        JButton checkInButton = createColoredButton("Check-in", new Color(102, 204, 255), "checkin_icon.png");
        JButton checkOutButton = createColoredButton("Check-out", new Color(255, 102, 102), "checkout_icon.png");
        JButton roomServiceButton = createColoredButton("Room Service", new Color(255, 204, 102),
                "roomservice_icon.png");
        JButton viewBillButton = createColoredButton("View Bill", new Color(153, 255, 153), "bill_icon.png");
        JButton removeGuestButton = createColoredButton("Remove Guest", new Color(255, 153, 204), "remove_icon.png");
        JButton refreshTableButton = createColoredButton("Refresh Table", new Color(204, 204, 255), "refresh_icon.png");
        JButton searchButton = createColoredButton("Search Guest", new Color(255, 255, 153), "search_icon.png");
        JButton sortNameButton = createColoredButton("Sort by Name", new Color(153, 204, 255), "sort_icon.png");
        JButton sortBillButton = createColoredButton("Sort by Bill", new Color(255, 153, 102), "sort_icon.png");
        JButton exportButton = createColoredButton("Export CSV", new Color(204, 255, 204), "export_icon.png");
        JButton statsButton = createColoredButton("View Stats", new Color(255, 204, 255), "stats_icon.png");
        JButton themeButton = createColoredButton("Toggle Theme", new Color(192, 192, 192), "theme_icon.png");

        // Add buttons to panel
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(roomServiceButton);
        buttonPanel.add(viewBillButton);
        buttonPanel.add(removeGuestButton);
        buttonPanel.add(refreshTableButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortNameButton);
        buttonPanel.add(sortBillButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(themeButton);

        background.add(buttonPanel, BorderLayout.SOUTH);

        // --- Button Actions ---

        // Check-in
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter guest name:");
                if (name != null && !name.isEmpty()) {
                    if (guests.containsKey(name)) {
                        JOptionPane.showMessageDialog(frame, "Guest already exists.");
                    } else {
                        guests.put(name, 0);
                        JOptionPane.showMessageDialog(frame, "Guest " + name + " checked in.");
                        loadTable();
                    }
                }
            }
        });

        // Check-out
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter guest name to check-out:");
                if (name != null && guests.containsKey(name)) {
                    int bill = guests.remove(name);
                    JOptionPane.showMessageDialog(frame, "Guest " + name + " checked out. Total bill: KSH " + bill);
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Guest not found.");
                }
            }
        });

        // Room Service
        roomServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] services = {
                        "Cleaning / Housekeeping - KSH 500",
                        "Laundry - KSH 300",
                        "Breakfast - KSH 400",
                        "Dinner - KSH 800"
                };
                String service = (String) JOptionPane.showInputDialog(frame,
                        "Select service:", "Room Service", JOptionPane.PLAIN_MESSAGE,
                        null, services, services[0]);
                if (service != null) {
                    String name = JOptionPane.showInputDialog(frame, "Enter guest name for this service:");
                    if (name != null && guests.containsKey(name)) {
                        int amount = Integer.parseInt(service.replaceAll("[^0-9]", ""));
                        guests.put(name, guests.get(name) + amount);
                        JOptionPane.showMessageDialog(frame, service + " added for " + name);
                        loadTable();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Guest not found.");
                    }
                }
            }
        });

        // View Bill
        viewBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter guest name to view bill:");
                if (name != null && guests.containsKey(name)) {
                    JOptionPane.showMessageDialog(frame, "Guest " + name + "'s current bill: KSH " + guests.get(name));
                } else {
                    JOptionPane.showMessageDialog(frame, "Guest not found.");
                }
            }
        });

        // Remove Guest
        removeGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter guest name to remove:");
                if (name != null && guests.remove(name) != null) {
                    JOptionPane.showMessageDialog(frame, "Guest " + name + " removed.");
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Guest not found.");
                }
            }
        });

        // Refresh Table
        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable();
            }
        });

        // Search Guest
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter guest name to search:");
                if (name != null && guests.containsKey(name)) {
                    JOptionPane.showMessageDialog(frame, "Guest " + name + " found. Bill: KSH " + guests.get(name));
                } else {
                    JOptionPane.showMessageDialog(frame, "Guest not found.");
                }
            }
        });

        // Sort by Name
        sortNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Map.Entry<String, Integer>> list = new ArrayList<>(guests.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getKey().compareTo(e2.getKey());
                    }
                });
                guests = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entry : list) {
                    guests.put(entry.getKey(), entry.getValue());
                }
                loadTable();
            }
        });

        // Sort by Bill
        sortBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Map.Entry<String, Integer>> list = new ArrayList<>(guests.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });
                guests = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entry : list) {
                    guests.put(entry.getKey(), entry.getValue());
                }
                loadTable();
            }
        });

        // Export CSV
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter writer = new PrintWriter("guests.csv");
                    writer.println("Guest Name,Bill");
                    for (Map.Entry<String, Integer> entry : guests.entrySet()) {
                        writer.println(entry.getKey() + "," + entry.getValue());
                    }
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Guest list exported to guests.csv");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error exporting: " + ex.getMessage());
                }
            }
        });

        // Statistics Dashboard
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guests.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No guests currently.");
                    return;
                }
                int totalGuests = guests.size();
                int totalBill = 0;
                int maxBill = 0;
                for (int bill : guests.values()) {
                    totalBill += bill;
                    if (bill > maxBill) {
                        maxBill = bill;
                    }
                }
                double avgBill = totalBill / (double) totalGuests;

                JOptionPane.showMessageDialog(frame,
                        "Total Guests: " + totalGuests +
                                "\nTotal Revenue: KSH " + totalBill +
                                "\nHighest Bill: KSH " + maxBill +
                                "\nAverage Bill: KSH " + avgBill);
            }
        });

        // Theme Switcher
        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!darkMode) {
                    frame.getContentPane().setBackground(Color.DARK_GRAY);
                    table.setBackground(Color.BLACK);
                    table.setForeground(Color.WHITE);
                    darkMode = true;
                } else {
                    frame.getContentPane().setBackground(null);
                    table.setBackground(Color.WHITE);
                    table.setForeground(Color.BLACK);
                    darkMode = false;
                }
            }
        });

        frame.setVisible(true);
    }

    // Helper to create colored buttons with icons and hover effect
    public static JButton createColoredButton(String text, Color color, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Set icon if available
        try {
            button.setIcon(new ImageIcon(iconPath));
        } catch (Exception ignored) {
        }
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
        return button;
    }

    public static void loadTable() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, Integer> entry : guests.entrySet()) {
            tableModel.addRow(new Object[] { entry.getKey(), "KSH " + entry.getValue() });
        }
    }
}