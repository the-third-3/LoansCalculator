package login_page;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;

 class RegisterPage extends JFrame {

    public RegisterPage() {
        setTitle("LOGIN PAGE");
        setSize(400, 400);
        setLayout(null);

        JLabel label = new JLabel("Welcome to The LOGIN Page");
        label.setBounds(100, 10, 200, 30);
        add(label);

        JLabel Email =new JLabel("ENTER EMAIL");
        final JTextField emailss=new JTextField(30);

        JLabel password =new JLabel("ENTER PASSWORD");
        final JTextField passw=new JTextField(30);

        Email.setBounds(30,70,200,30);
        add(Email);
        emailss.setBounds(150,70,200,30);
        add(emailss);

        password.setBounds(30,120,200,30);
        add(password);
        passw.setBounds(150,120,200,30);
        add(passw);
        
        JButton login= new JButton("LOGIN");
        login.setBounds(124,180,140,40);
        add(login);
        
         JLabel backToLogin = new JLabel("<html>Don't have an account ? <u> Register</u></html>");
        backToLogin.setBounds(100, 260, 300, 30);
        backToLogin.setForeground(Color.BLUE);
        backToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backToLogin);
        
        backToLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new LoginPage();
                dispose(); // close register
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}