package registrationform;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
                       
public class RegistrationForm {
     static String URL="jdbc:mysql://localhost:3306/ongili";


    public static void main(String[] args) {

            final JFrame mike= new JFrame();
            mike.setLayout(null);
            mike.getContentPane().setBackground(Color.RED);
            JLabel title = new JLabel("New User Registration");
            title.setFont(new Font("Arial",Font.BOLD,27));
            JLabel FName = new JLabel("First Name");
            JLabel LName = new JLabel("Last Name");
            JLabel Email = new JLabel("Email Address");
            JLabel UserN = new JLabel("Username");
            JLabel Password = new JLabel("Password");
            JLabel mobile = new JLabel("Phone Number");
            title.setBounds(230,0,370,70);
            FName.setBounds(50,90,100,40);
            LName.setBounds(50,160,100,40);
            Email.setBounds(50,240,100,40);
            UserN.setBounds(400,90,100,40);
            Password.setBounds(400,160,100,40);
            mobile.setBounds(400,240,100,40);

            
            //textfields
            final JTextField firstN=new JTextField(20);
            final JTextField lastN = new JTextField(20);
             final JTextField address=new JTextField(20);
            final JTextField user = new JTextField(20);
             final JPasswordField password=new JPasswordField(20);
            final JTextField phoneno = new JTextField(20);
            
            
            firstN.setBounds(180,90,180,40);
            lastN.setBounds(180,160,180,40);
            address.setBounds(180,240,180,40);
            user.setBounds(500,90,180,40);
            password.setBounds(500,160,180,40);
            phoneno.setBounds(500,240,180,40);
            
            
            JButton insert = new JButton("REGISTER");
            JButton retrieve = new JButton("NEXT");
            JButton clear = new JButton("DELETE");
            JButton previuos = new JButton("PREVIOUS");
            JButton exit = new JButton("EXIT");
            
            //action listenners
       
                  

            
             
            
            insert.setBounds(50,380,100,40);
            clear.setBounds(180,380,100,40);
            retrieve.setBounds(310,380,100,40);
            previuos.setBounds(440,380,100,40);
            exit.setBounds(570,380,100,40);
             mike.add( title);
            mike.add( FName);
            mike.add(LName);
            mike.add(Email);
            mike.add(UserN);
            mike.add(Password);
            mike.add(mobile);
            mike.add( firstN);
            mike.add(lastN);
            mike.add(address);
            mike.add(user);
            mike.add(password);
            mike.add(phoneno);

            mike.add(insert);
            mike.add(retrieve);
            mike.add(exit);
             mike.add(clear);
            mike.add(previuos);
            
            
                  Connection con = null;
        Statement st = null;
        PreparedStatement pst = null;
        final ResultSet rs = null;
            
            
                  try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ongili",
                    "root",
                    ""
            );

            st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = st.executeQuery("SELECT * FROM registration");

        } catch (Exception e) {
            e.printStackTrace();
        }
             
                  
                  insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                     Connection con = null;
                    PreparedStatement pstInsert = con.prepareStatement(
                            "INSERT INTO registration (FirstName , LastNmae, EmailAddress, UserName, Password, MobileNumber) VALUES (?,?,?,?,?,?)"
                    );

                    pstInsert.setString(1, firstN.getText().trim());
                    pstInsert.setString(2, lastN.getText().trim());
                    pstInsert.setString(3, address.getText().trim());
                    pstInsert.setString(4, String.valueOf(password.getPassword()));
                    pstInsert.setString(5, user.getText().trim());
                    pstInsert.setString(6,phoneno.getText().trim());

                    pstInsert.executeUpdate();
                    JOptionPane.showMessageDialog(mike, "Record Inserted");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
                  
                  
                  clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                        Connection con = null;
       
                    PreparedStatement pst = con.prepareStatement(
                        "DELETE FROM registration WHERE MobileNumber = ?"
                    );

                    pst.setString(1, phoneno.getText().trim());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(mike, "Record Deleted");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
                  
                    retrieve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.next()) {
                        firstN.setText(rs.getString("FirstName"));
                        lastN.setText(rs.getString("LastNmae"));
                        address.setText(rs.getString("EmailAddress"));
                        password.setText(rs.getString("Password"));
                        user.setText(rs.getString("UserName"));
                        phoneno.setText(rs.getString("MobileNumber"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

       
        previuos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.previous()) {
                        firstN.setText(rs.getString("FirstName"));
                        lastN.setText(rs.getString("LastNmae"));
                        address.setText(rs.getString("EmailAddress"));
                        password.setText(rs.getString("Password"));
                        user.setText(rs.getString("UserName"));
                        phoneno.setText(rs.getString("MobileNumber"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

       
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

                  
           
            mike.setSize(800,600);
            mike.setLayout(null);
            mike.setVisible(true);  



 
            
           
                


           

            }

            }











                 Connection con = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
            
            
                  try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ongili",
                    "root",
                    ""
            );

            st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = st.executeQuery("SELECT * FROM registration");

        } catch (Exception e) {
            e.printStackTrace();
        }
             
                  
                  insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                     Connection con = null;
                    PreparedStatement pstInsert = con.prepareStatement(
                            "INSERT INTO registration (firstN, lastN, address, password, user, phoneno) VALUES (?,?,?,?,?,?)"
                    );

                    pstInsert.setString(1, firstN.getText().trim());
                    pstInsert.setString(2, lastN.getText().trim());
                    pstInsert.setString(3, address.getText().trim());
                    pstInsert.setString(4, String.valueOf(password.getPassword()));
                    pstInsert.setString(5, user.getText().trim());
                    pstInsert.setString(6,phoneno.getText().trim());

                    pstInsert.executeUpdate();
                    JOptionPane.showMessageDialog(mike, "Record Inserted");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });