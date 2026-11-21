package login_page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class LoginPage extends JFrame {

    public LoginPage() {
      final JFrame forth=new JFrame("LOGIN VIEW");
        JLabel title = new JLabel("LOGIN WINDOW");
        JLabel Name =new JLabel("ENTER NAME");
        final JTextField namess=new JTextField(30);
        
        JLabel Email =new JLabel("ENTER EMAIL");
         final JTextField emailss=new JTextField(30);
         
         JLabel password =new JLabel("ENTER PASSWORD");
          final JTextField passw=new JTextField(30);
          
        JLabel Confpassword =new JLabel("CONFIRM PASSWORD");
         final JTextField conpassw=new JTextField(30);
         
           JButton login= new JButton("REGISTER");
       // JButton register= new JButton("REGISTER");
         JButton reset= new JButton("RESET");
        
        title.setBounds(150,15,200,50);
        forth.add(title);
        
         Name.setBounds(30,80,200,30);
         forth.add(Name);
         namess.setBounds(200,80,200,30);
         forth.add(namess);
        
         Email.setBounds(30,130,200,30);
        forth.add(Email);
          emailss.setBounds(200,130,200,30);
        forth.add(emailss);
        
         password.setBounds(30,180,200,30);
        forth.add(password);
         passw.setBounds(200,180,200,30);
        forth.add(passw);
        
         Confpassword.setBounds(30,230,200,30);
        forth.add(Confpassword);
         conpassw.setBounds(200,230,200,30);
        forth.add(conpassw);
        
      
         login.setBounds(80,330,120,35);
         forth.add(login);
         reset.setBounds(220,330,120,35);
         forth.add(reset);
         
         
       reset.addActionListener(new ActionListener()

      {

      public void actionPerformed(ActionEvent e)

      {
            namess.setText("");

            emailss.setText("");

            passw.setText("");

            conpassw.setText("");

      }

      });
       
       JLabel registerLink = new JLabel("<html>Already have an account, <u>LOGIN</u></html>");
        registerLink.setBounds(120, 370, 250, 30);
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forth.add(registerLink);
        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new RegisterPage();  // open register frame
                forth.dispose();    // close login frame
            }
        });
        
       
ImageIcon bgImage = new ImageIcon("src/login_page/IMAGES/background-code.jpg");
JLabel background = new JLabel(bgImage);

// Set size to match frame
background.setBounds(2, 0, 400, 50);

// Add it FIRST
forth.add(background);
            
        
        forth.setLayout(null);
        forth.setSize(450,500);
        forth.setVisible(true);
        
        
        
    }
}