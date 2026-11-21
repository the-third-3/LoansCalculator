Package currconv;

Import java.awt.*;

Import javax.swing.*;

Import java.awt.event.*;

Public class CurrencyConverter {

Public static void main(String[] args) {

JFrame new4 = new JFrame(“MY DETAILS”);

     New4.setLayout(null); 

     New4.getContentPane().setBackground(Color.red);

     

            

      New4.setSize(320,600);

    New4.setVisible(true);

    

     

    JLabel sing= new JLabel(“SINGAPORE DOLLARS”);

    JLabel usdss= new JLabel(“US DOLLARS:”);

    JLabel euro= new JLabel(“EUROS :”);

    JTextField sin = new JTextField(40);

    JTextField dust = new JTextField(40);

    JTextField eur = new JTextField(40);

    JLabel ugandaS= new JLabel(“UGANDAN SHILLINGS”);

    JLabel Tanzanin= new JLabel(“TZ SHILLINGS:”);

    JLabel Rwandeese= new JLabel(“RWANDAN FRANC :”);

    JTextField uganda = new JTextField(40);

    JTextField tanzan = new JTextField(40);

    JTextField rwanda = new JTextField(40);

    

         

    JLabel southAF= new JLabel(“SOUTH A. RAND”);

    JLabel BRITIS= new JLabel(“STERLING POUNDS”);

    JLabel SAUDI= new JLabel(“SAUDI RIYAL :”);

    JLabel indian= new JLabel(“INDIAN RUPEES”);

    JLabel CHINEESE= new JLabel(“CHINESE YEN:”);

    JLabel australia= new JLabel(“AUSTR DOLLARS :”);

    JLabel nigeria= new JLabel(“NIGERIAN NIRAS:”);

    JLabel ghana= new JLabel(“GHANAINIA CEDI:”);

    JLabel zimbabwe= new JLabel(“ZIMBABWE GOLD:”);



    JTextField southafr = new JTextField(40);

    JTextField british = new JTextField(40);

    JTextField saudiar = new JTextField(40);

    JTextField india = new JTextField(40);

    JTextField china = new JTextField(40);

    JTextField autral = new JTextField(40);

    JTextField nigerr = new JTextField(40);

    JTextField ghannaa = new JTextField(40);

    JTextField zimbab = new JTextField(40);

   

    New4.add(sing);

    New4.add(usdss);

    New4.add(sin);

    New4.add(euro);

    New4.add(dust);

    New4.add(eur);

    New4.add(ugandaS);

    New4.add(Tanzanin);

    New4.add(Rwandeese);

    New4.add(uganda);

    New4.add(tanzan);

    New4.add(rwanda);

     

    New4.add(southAF);

    New4.add(BRITIS);

    New4.add(SAUDI);

    New4.add(indian);

    New4.add(CHINEESE);

    New4.add(australia);

    New4.add(nigeria);

    New4.add(ghana);

    New4.add(zimbabwe);

    New4.add(zimbab);

    New4.add(southafr);

    New4.add(british);

    New4.add(saudiar);

    New4.add(india);

    New4.add(china);

    New4.add(autral);

    New4.add(nigerr);

    New4.add(ghannaa);

   

    

    

       

    JButton conv = new JButton(“CONVERT”);

    New4.add(conv);

    JButton clear=new JButton(“CLEAR ALL”);

     New4.add(clear);

    

      Conv.addActionListener(new ActionListener()

      {

      Public void actionPerformed(ActionEvent e)

      {

          

          If (!sin.getText().isEmpty()) {

            Double sn = Double.parseDouble(sin.getText());

            Dust.setText(String.valueOf( sn * 0.78));

            Eur.setText(String.valueOf(sn * 0.66));

            Uganda.setText(String.valueOf(sn * 2713.43));

             Tanzan.setText(String.valueOf( sn * 1919.08));

            Rwanda.setText(String.valueOf(sn * 1125.80));

            Southafr.setText(String.valueOf(sn * 13.44));

            British.setText(String.valueOf( sn * 0.74));

            Saudiar.setText(String.valueOf(sn * 2.91));

            India.setText(String.valueOf(sn * 68.83));

             China.setText(String.valueOf( sn * 5.54));

            Autral.setText(String.valueOf(sn * 1.18));

            Nigerr.setText(String.valueOf(sn * 1156.85));

            Zimbab.setText(String.valueOf(sn * 250));

            Ghannaa.setText(String.valueOf(sn * 9.59));

           

            

       

        }

           Else if (!eur.getText().isEmpty()) {

            Double eu = Double.parseDouble(eur.getText());

            Dust.setText(String.valueOf( eu * 0.85));

            Sin.setText(String.valueOf( eu * 1.3));

             Uganda.setText(String.valueOf(eu *4107.00));

             Tanzan.setText(String.valueOf( eu * 2908.6000));

            Rwanda.setText(String.valueOf(eu * 1704.00));

            Southafr.setText(String.valueOf(eu * 20.33));

            British.setText(String.valueOf( eu * 0.74));

            Saudiar.setText(String.valueOf(eu * 4.41));

            India.setText(String.valueOf(eu * 104.17));

             China.setText(String.valueOf( eu * 8.38));

            Autral.setText(String.valueOf(eu * 1.78));

            Nigerr.setText(String.valueOf(eu * 1750.90));

            Zimbab.setText(String.valueOf(eu * 443.25));

            Ghannaa.setText(String.valueOf(eu * 14.49));

            

            

        }

           

            Else if (!dust.getText().isEmpty()) {

            Double ds = Double.parseDouble(dust.getText());

            Sin.setText(String.valueOf( ds * 0.85));

            Eur.setText(String.valueOf( ds * 1.3));

             Uganda.setText(String.valueOf(ds *3495.91));

             Tanzan.setText(String.valueOf( ds * 2480.00));

            Rwanda.setText(String.valueOf(ds * 1450.45));

            Southafr.setText(String.valueOf(ds * 17.3));

            British.setText(String.valueOf( ds * 0.74));

            Saudiar.setText(String.valueOf(ds * 3.75));

            India.setText(String.valueOf(ds * 88.67));

             China.setText(String.valueOf( ds * 7.13));

            Autral.setText(String.valueOf(ds * 1.51));

            Nigerr.setText(String.valueOf(ds * 1490.26));

            Zimbab.setText(String.valueOf(ds * 322));

            Ghannaa.setText(String.valueOf(ds * 12.35));

        }

           

          

           Else if (!uganda.getText().isEmpty()) {

            Double ugnda = Double.parseDouble(uganda.getText());

            Dust.setText(String.valueOf( ugnda * 0.00028));

            Sin.setText(String.valueOf( ugnda * 0.00037));

             Eur.setText(String.valueOf(ugnda *0.00024));

             Tanzan.setText(String.valueOf( ugnda * 0.70));

            Rwanda.setText(String.valueOf(ugnda * 0.41));

            Southafr.setText(String.valueOf(ugnda * 0.0049));

            British.setText(String.valueOf( ugnda * 0.00021));

            Saudiar.setText(String.valueOf(ugnda * 0.0011));

            India.setText(String.valueOf(ugnda * 0.025));

             China.setText(String.valueOf( ugnda * 0.002));

            Autral.setText(String.valueOf(ugnda *0.00043));

            Nigerr.setText(String.valueOf(ugnda * 0.043));

            Zimbab.setText(String.valueOf(ugnda * 0.103));

            Ghannaa.setText(String.valueOf(ugnda * 0.0035));

        }

           

            Else if (!tanzan.getText().isEmpty()) {

            Double tz = Double.parseDouble(tanzan.getText());

            Sin.setText(String.valueOf( tz * 0.0033));

            Eur.setText(String.valueOf( tz * 0.00034));

             Uganda.setText(String.valueOf(tz *1.42));

             Dust.setText(String.valueOf( tz * 0.00040));

            Rwanda.setText(String.valueOf(tz * 0.59));

            Southafr.setText(String.valueOf(tz * 0.007));

            British.setText(String.valueOf( tz * 0.0003));

            Saudiar.setText(String.valueOf(tz * 0.0015));

            India.setText(String.valueOf(tz * 0.36));

             China.setText(String.valueOf( tz * 0.003));

            Autral.setText(String.valueOf(tz * 0.00061));

            Nigerr.setText(String.valueOf(tz * 0.6));

            Zimbab.setText(String.valueOf(tz * 0.00109));

            Ghannaa.setText(String.valueOf(tz * 0.005));

        }

           

           Else if (!rwanda.getText().isEmpty()) {

            Double rwand = Double.parseDouble(rwanda.getText());

            Dust.setText(String.valueOf( rwand * 0.0007));

            Sin.setText(String.valueOf( rwand * 0.0009));

             Uganda.setText(String.valueOf(rwand *2.42));

             Tanzan.setText(String.valueOf( rwand * 1.7));

            Eur.setText(String.valueOf(rwand * 0.00059));

            Southafr.setText(String.valueOf(rwand * 0.012));

            British.setText(String.valueOf( rwand * 0.00051));

            Saudiar.setText(String.valueOf(rwand *0.0026));

            India.setText(String.valueOf(rwand * 0.061));

             China.setText(String.valueOf( rwand * 0.0049));

            Autral.setText(String.valueOf(rwand * 0.001));

            Nigerr.setText(String.valueOf(rwand * 1.03));

            Zimbab.setText(String.valueOf(rwand * 0.019));

            Ghannaa.setText(String.valueOf(rwand * 0.0085));

        }

           

            Else if (!southafr.getText().isEmpty()) {

            Double SAFR= Double.parseDouble(southafr.getText());

            Sin.setText(String.valueOf( SAFR * 0.0735));

            Eur.setText(String.valueOf( SAFR * 1.3));

             Uganda.setText(String.valueOf(SAFR *201.71));

             Tanzan.setText(String.valueOf( SAFR * 200.00));

            Rwanda.setText(String.valueOf(SAFR * 200.45));

            Dust.setText(String.valueOf(SAFR * 0.0577));

            British.setText(String.valueOf( SAFR * 0.045));

            Saudiar.setText(String.valueOf(SAFR * 0.22));

            India.setText(String.valueOf(SAFR * 5.13));

             China.setText(String.valueOf( SAFR * 0.4117));

            Autral.setText(String.valueOf(SAFR * 0.0874));

            Nigerr.setText(String.valueOf(SAFR * 1.1));

            Ghannaa.setText(String.valueOf(SAFR * 0.635));

        }

           

           Else if (!british.getText().isEmpty()) {

            Double brtsh = Double.parseDouble(british.getText());

            Dust.setText(String.valueOf( brtsh * 1.30));

            Sin.setText(String.valueOf( brtsh * 1.73));

             Uganda.setText(String.valueOf(brtsh *4706.74));

             Tanzan.setText(String.valueOf( brtsh * 3322.85));

            Rwanda.setText(String.valueOf(brtsh * 1948.7));

            Southafr.setText(String.valueOf(brtsh * 23.3));

            Eur.setText(String.valueOf( brtsh * 1.14));

            Saudiar.setText(String.valueOf(brtsh * 5.05));

            India.setText(String.valueOf(brtsh * 118.74));

             China.setText(String.valueOf( brtsh * 9.54));

            Autral.setText(String.valueOf(brtsh * 2.04));

            Nigerr.setText(String.valueOf(brtsh *1995.47));

            Zimbab.setText(String.valueOf(brtsh * 488.98));

            Ghannaa.setText(String.valueOf(brtsh * 16.60));

        }

           

            Else if (!saudiar.getText().isEmpty()) {

            Double saudi = Double.parseDouble(saudiar.getText());

            Sin.setText(String.valueOf( saudi * 0.34));

            Eur.setText(String.valueOf( saudi * 0.23));

             Uganda.setText(String.valueOf(saudi *934.11));

             Tanzan.setText(String.valueOf( saudi * 655.850));

            Rwanda.setText(String.valueOf(saudi * 386.74));

            Southafr.setText(String.valueOf(saudi * 4.64));

            British.setText(String.valueOf( saudi * 0.20));

            Dust.setText(String.valueOf(saudi * 0.275));

            India.setText(String.valueOf(saudi * 23.67));

             China.setText(String.valueOf( saudi * 1.9));

            Autral.setText(String.valueOf(saudi * 0.41));

            Nigerr.setText(String.valueOf(saudi * 397.66));

            Zimbab.setText(String.valueOf(saudi * 88.43));

            Ghannaa.setText(String.valueOf(saudi * 3.29));

//jljfoup

//

}

            Else if (!india.getText().isEmpty()) {

            Double ind = Double.parseDouble(india.getText());

            Sin.setText(String.valueOf( ind * 0.015));

            Eur.setText(String.valueOf( ind * 0.0096));

             Uganda.setText(String.valueOf(ind *39.50));

             Tanzan.setText(String.valueOf( ind * 27.790));

            Rwanda.setText(String.valueOf(ind * 16.36));

            Southafr.setText(String.valueOf(ind *0.20));

            British.setText(String.valueOf( ind * 0.0084));

            Saudiar.setText(String.valueOf(ind * 0.042));

            Dust.setText(String.valueOf(ind * 0.0096));

             China.setText(String.valueOf( ind * 0.08));

            Autral.setText(String.valueOf(ind * 1.51));

            Nigerr.setText(String.valueOf(ind * 16.826));

            Zimbab.setText(String.valueOf(ind * 3.6258));

            Ghannaa.setText(String.valueOf(ind * 12.35));

            

        } else if (!china.getText().isEmpty()) {

            Double chin = Double.parseDouble(china.getText());

            Sin.setText(String.valueOf( chin * 0.18));

            Eur.setText(String.valueOf( chin * 0.12));

             Uganda.setText(String.valueOf(chin *491.26));

             Tanzan.setText(String.valueOf( chin * 345.62));

            Rwanda.setText(String.valueOf(chin *203.39));

            Southafr.setText(String.valueOf(chin * 2.43));

            British.setText(String.valueOf( chin * 0.1));

            Saudiar.setText(String.valueOf(chin * 0.53));

            India.setText(String.valueOf(chin * 12.43));

             Dust.setText(String.valueOf( chin * 0.14));

            Autral.setText(String.valueOf(chin * 0.21));

            Nigerr.setText(String.valueOf(chin * 209.11));

            Zimbab.setText(String.valueOf(chin *50.797 ));

            Ghannaa.setText(String.valueOf(chin * 12.35));

            

        } else if (!autral.getText().isEmpty()) {

            Double austr = Double.parseDouble(autral.getText());

            Sin.setText(String.valueOf( austr * 0.85));

            Eur.setText(String.valueOf( austr * 0.56));

             Uganda.setText(String.valueOf(austr *2307.26));

             Tanzan.setText(String.valueOf( austr * 1623.13));

            Rwanda.setText(String.valueOf(austr * 955.74));

            Southafr.setText(String.valueOf(austr * 11.41));

            British.setText(String.valueOf( austr * 0.49));

            Saudiar.setText(String.valueOf(austr * 2.475));

            India.setText(String.valueOf(austr * 88.67));

             China.setText(String.valueOf( austr * 4.7));

            Dust.setText(String.valueOf(austr *0.66));

            Nigerr.setText(String.valueOf(austr * 982.14));

            Zimbab.setText(String.valueOf(austr * 322));

            Ghannaa.setText(String.valueOf(austr * 8.15));

            

        } else if (!nigerr.getText().isEmpty()) {

            Double nigr = Double.parseDouble(nigerr.getText());

            Sin.setText(String.valueOf( nigr * 0.00085));

            Eur.setText(String.valueOf( nigr * 0.00057));

             Uganda.setText(String.valueOf(nigr *2.35));

             Tanzan.setText(String.valueOf( nigr * 1.66));

            Rwanda.setText(String.valueOf(nigr * 0.97));

            Southafr.setText(String.valueOf(nigr * 0.012));

            British.setText(String.valueOf( nigr * 0.00050));

            Saudiar.setText(String.valueOf(nigr * 0.0025));

            India.setText(String.valueOf(nigr * 0.059));

             China.setText(String.valueOf( nigr *0.0048));

            Autral.setText(String.valueOf(nigr * 0.0010));

            Dust.setText(String.valueOf(nigr * 0.00067));

            Zimbab.setText(String.valueOf(nigr * 0.196));

            Ghannaa.setText(String.valueOf(nigr * 0.0083));

        } else if (!ghannaa.getText().isEmpty()) {

            Double ghan = Double.parseDouble(ghannaa.getText());

            Sin.setText(String.valueOf( ghan * 0.10));

            Eur.setText(String.valueOf( ghan * 0.069));

             Uganda.setText(String.valueOf(ghan *282.91));

             Tanzan.setText(String.valueOf( ghan * 200.13));

            Rwanda.setText(String.valueOf(ghan * 117.4));

            Southafr.setText(String.valueOf(ghan * 1.40));

            British.setText(String.valueOf( ghan * 0.060));

            Saudiar.setText(String.valueOf(ghan * 0.3));

            India.setText(String.valueOf(ghan * 7.18));

             China.setText(String.valueOf( ghan * 0.58));

            Autral.setText(String.valueOf(ghan * 0.12));

            Nigerr.setText(String.valueOf(ghan * 120.68));

            Zimbab.setText(String.valueOf(ghan * 322));

            Dust.setText(String.valueOf(ghan * 0.081));

        }

      }

      });

      

      

      

       Clear.addActionListener(new ActionListener()

      {

      Public void actionPerformed(ActionEvent e)

      {

         

          Sin.setText(“”);

            Dust.setText(“”);

           Eur.setText(String.valueOf(“”));

            Uganda.setText(“”);

             Tanzan.setText(“”);

            Rwanda.setText(“”);

            Southafr.setText(“”);

            British.setText(“”);

            Saudiar.setText(“”);

            India.setText(“”);

             China.setText(“”);

            Autral.setText(“”);

            Nigerr.setText(“”);

            Zimbab.setText(“”);

            Ghannaa.setText(“”);

         

      }});

     

     Sing.setBounds(5,10,130,30); 

     Sin.setBounds(145,10,140,25);

     

     Usdss.setBounds(5,40,130,30); 

     Dust.setBounds(145,40,140,25);

     

     Euro.setBounds(5,70,100,30); 

     Eur.setBounds(145,70,140,25);

     

     ugandaS.setBounds(5,100,130,30); 

     uganda.setBounds(145,100,140,25);

     

     Tanzanin.setBounds(5,130,130,30); 

     Tanzan.setBounds(145,130,140,25);

     

     Rwandeese.setBounds(5,160,130,30); 

     Rwanda.setBounds(145,160,140,25);

     

     

      

     southAF.setBounds(5,340,130,30); 

     southafr.setBounds(145,340,140,25);

     

     BRITIS.setBounds(5,190,130,30); 

     British.setBounds(145,190,140,25);

     

     SAUDI.setBounds(5,220,100,30); 

     Saudiar.setBounds(145,220,140,25);

     

     Indian.setBounds(5,250,130,30); 

     India.setBounds(145,250,140,25);

     

     CHINEESE.setBounds(5,280,130,30); 

     China.setBounds(145,280,140,25);

     

     Australia.setBounds(5,310,130,30); 

     Autral.setBounds(145,310,140,25);

     

     Nigeria.setBounds(5,430,130,30); 

     Nigerr.setBounds(145,430,140,25);

     

     Ghana.setBounds(5,370,130,30); 

     Ghannaa.setBounds(145,370,140,25);

     

     Zimbabwe.setBounds(5,400,130,30); 

     Zimbab.setBounds(145,400,140,25);

     

     Conv.setBounds(50,500, 100, 40);

     Clear.setBounds(154,500,100,40);

       

        

           

          

    

    

    

    

}

}
