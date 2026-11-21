/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanscalculator;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class LoansCalculator {
public static void main(String[] args) {

    JFrame new4 = new JFrame("LOANS CALCULATOR");

     new4.setLayout(null); 

     new4.getContentPane().setBackground(Color.red);

     

            

      new4.setSize(460,450);

    new4.setVisible(true);

    

     JLabel Name= new JLabel("Borrower:");

    JLabel Principle= new JLabel("Principle:");

    JLabel interest= new JLabel("Interest Rate:");

    

     JLabel PaymentPeriod= new JLabel("Period in Months:");

    JLabel interestpaid= new JLabel("Total Interest:");

    JLabel Total= new JLabel("Total Amount:");

    

    final JTextField Nametxt = new JTextField(40);

    final JTextField Principletxt = new JTextField(40);

    final JTextField ratetxt = new JTextField(40);

     final JTextField period = new JTextField(40);

    final JTextField TotalInterest = new JTextField(40);

    final JTextField TotalAmount = new JTextField(40);

    

       TotalInterest.setEditable(false);

            TotalAmount.setEditable(false);

    

    

    

    new4.add(Name);

    new4.add(Principle);

    new4.add(interest);

    new4.add(Nametxt);

    new4.add(Principletxt);

    new4.add(ratetxt);

    

    new4.add(PaymentPeriod);

    new4.add(interestpaid);

    new4.add(Total);

    new4.add(period);

    new4.add(TotalInterest);

    new4.add(TotalAmount);

    

    

    Name.setBounds(35,10,100,30); Nametxt.setBounds(150,10,160,25);

      Principle.setBounds(35,40,100,30); Principletxt.setBounds(150,40,160,25);

       interest.setBounds(35,70,100,30); ratetxt.setBounds(150,75,160,25);

        

    PaymentPeriod.setBounds(35,110,100,30); period.setBounds(150,110,160,25);

      interestpaid.setBounds(35,150,100,30); TotalInterest.setBounds(150,150,160,25);

       Total.setBounds(35,180,100,30); TotalAmount.setBounds(150,185,160,25);

       

       

         JButton Convert=new JButton("GET RESULTS");

       JButton clear=new JButton("CLEAR ALL");

       

        Convert.setBounds(70,250,120,35);

      clear.setBounds(210,250,120,35);

      

       new4.add(Convert);

     new4.add(clear);

     

        Convert.addActionListener(new ActionListener()

      {

      public void actionPerformed(ActionEvent e)

      {

         Double p=Double.parseDouble(Principletxt.getText());

          double r=Double.parseDouble(ratetxt.getText());

          double t=Double.parseDouble(period.getText());

         

         

         double logA =Math.log(p) + t*Math.log(1+r/1200);

         double Amount=Math.exp(logA);

         

         TotalInterest.setText(String.valueOf(Amount-p));

         

          TotalAmount.setText(String.valueOf(Amount));

          

        

      

         

      } 

      }); 

        

        

          clear.addActionListener(new ActionListener()

      {

      public void actionPerformed(ActionEvent e)

      {

          

          Double p=Double.parseDouble(Principletxt.getText());

          double r=Double.parseDouble(ratetxt.getText());

          double t=Double.parseDouble(period.getText());

          

            Nametxt.setText("");

            ratetxt.setText("");

         period.setText(String.valueOf(""));

          Principletxt.setText("");

            TotalInterest.setText("");

         TotalAmount.setText(String.valueOf(""));

         

      }

      }); 

}
}

