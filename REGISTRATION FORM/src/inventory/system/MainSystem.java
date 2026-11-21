package inventory.system;

import javax.swing.SwingUtilities;

public class MainSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize Database Tables
            DatabaseHelper.initDatabase();
            // Launch Login Screen
            new LoginFrame();
        });
    }
}
login.setBounds(80,330,120,35);
         forth.add(login);