package distanceconv;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class DistanceConverter {
public static void main(String[] args) {

   JFrame new4 = new JFrame("MY DETAILS");

     new4.setLayout(null); 

     new4.getContentPane().setBackground(Color.cyan);

     

            

      new4.setSize(320,300);

    new4.setVisible(true);

    

     JTextField kilom = new JTextField(40);

    JTextField metre = new JTextField(40);

    JTextField milimetre = new JTextField(40);

    JTextField miles = new JTextField(40);

    JTextField foots = new JTextField(40);

     JTextField cms = new JTextField(40);

     

     metre.setEditable(false);

      milimetre.setEditable(false);

       miles.setEditable(false);

        foots.setEditable(false);

         cms.setEditable(false);

    

    

    

    new4.add(kilom);

    new4.add(metre);

    new4.add(milimetre);

    new4.add(miles);

    new4.add(foots);

    new4.add(cms);

    

    

    JLabel sing= new JLabel("KILOMETRES");

    JLabel usdss= new JLabel("METRES:");

    JLabel euro= new JLabel("MILIMETRES:");

    JLabel ugandaS= new JLabel("MILES");

    JLabel Tanzanin= new JLabel("FEET");

     JLabel centim= new JLabel("CENTIMETRES");

    

     

    new4.add(sing);

    new4.add(usdss);

    new4.add(ugandaS);

    new4.add(euro);

    new4.add(Tanzanin);

    new4.add(centim);

 

    

    

     kilom.setBounds(20,10,130,30); 

     sing.setBounds(175,10,140,25);

     

     metre.setBounds(20,40,130,30); 

     usdss.setBounds(175,40,140,25);

     

     milimetre.setBounds(20,70,130,30); 

     euro.setBounds(175,70,140,25);

     

     miles.setBounds(20,100,130,30); 

     ugandaS.setBounds(175,100,140,25);

     

     foots.setBounds(20,130,130,30); 

     Tanzanin.setBounds(175,130,140,25);

     

       JButton conv = new JButton("CONVERT");

    new4.add(conv);

    JButton clear=new JButton("CLEAR ALL");

     new4.add(clear);

     

     new4.add(conv);

    new4.add(clear);

      

     conv.setBounds(30,170, 100, 40);

     clear.setBounds(140,170,100,40);

     

       conv.addActionListener(new ActionListener(){

       public void actionPerformed(ActionEvent e){

           

           double km =Double.parseDouble(kilom.getText());

           metre.setText(String.valueOf(km*1000));

            milimetre.setText(String.valueOf(km*1000000000));

             miles.setText(String.valueOf(km*0.621371));

              foots.setText(String.valueOf(km*3280.84));

               cms.setText(String.valueOf(km*1000000));



               

               

       }

        });

       clear.addActionListener(new ActionListener(){

           public void actionPerformed(ActionEvent e){

           kilom.setText(String.valueOf(""));

           metre.setText(String.valueOf(""));

           milimetre.setText(String.valueOf(""));

           miles.setText(String.valueOf(""));

           foots.setText(String.valueOf(""));

           cms.setText(String.valueOf(""));

           }

       

       }

       );

     

     

}
}

