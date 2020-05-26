/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author Magda
 */
public class Swing5 {
    
    public boolean getWartosc(String i ){
            
        if (i=="Tak"){
            return true;
        }
        else{
             return false;
        }
           
        }
    Swing5(Zbior z1){
        BufferedImage myImage = null;
         try{
         myImage = ImageIO.read(new File("images\\img2.jpg"));
         } catch(IOException e) {
         }
        JFrame ramka = new JFrame("Rezerwacje");
        ramka.setContentPane(new ImagePanel(myImage));
          JButton powrot = new JButton("Powrót");
        powrot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        powrot.setBounds(50,50,150,50);
        /////////////////////////
        
        JLabel naglowek = new JLabel();
        naglowek.setText("<html><p>Dodaj budynek&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><hr/>"
                + "<p>Nazwa</p><p></p><p>Winda</p><p></p><p>Wi-Fi</p>"
                + "<p></p><p>Gastronomia</p>"
                + "</html>");
        naglowek.setBounds(50,150,450,400);
        naglowek.setFont(new Font("Serif", Font.PLAIN, 21));
        naglowek.setForeground(Color.BLACK);
        naglowek.setOpaque(true);
        naglowek.setBackground(Color.white);
        naglowek.setBorder(
            new CompoundBorder(
                new CompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY,10),
                    BorderFactory.createLineBorder(Color.GRAY,6)     
                ),
            BorderFactory.createLineBorder(Color.WHITE,10)
            )
        );
        naglowek.setVerticalAlignment(SwingConstants.TOP);
        ramka.add(naglowek);
        ///////////////
        JTextField tNazwaB = new JTextField("Podaj nazwę");
        tNazwaB.setBounds(26, 95, 190, 25);
        naglowek.add(tNazwaB);
        ////////////////
        String windaTN[] = {"Tak","Nie"};
        JComboBox winda = new JComboBox(windaTN);
        winda.setBounds(26,150,190,25);
        winda.setBackground(Color.WHITE);
        naglowek.add(winda);
        
        String wifiTN[] = {"Tak","Nie"};
        JComboBox wifi = new JComboBox(wifiTN);
        wifi.setBounds(26,205,190,25);
        wifi.setBackground(Color.WHITE);
        naglowek.add(wifi);
        
        String gastronomiaTN[] = {"Tak","Nie"};
        JComboBox gastronomia = new JComboBox(gastronomiaTN);
        gastronomia.setBounds(26,260,190,25);
        gastronomia.setBackground(Color.WHITE);
        naglowek.add(gastronomia);
        
    /**
     *
     * @param i
     * @return
     */
    
        
        
        
        JButton dodaj = new JButton("Dodaj");
        
        dodaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                Budynek tmp = new Budynek();
                tmp = new Budynek(tNazwaB.getText(),getWartosc(winda.getSelectedItem().toString()),getWartosc(gastronomia.getSelectedItem().toString()),getWartosc(wifi.getSelectedItem().toString()));
                tmp.IDbud = z1.getSpisBudynkow().size()+1;
                z1.DodajBudynek(tmp);
                System.out.println(tmp.toString());
                
                new Swing6(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        dodaj.setBounds(26,300,150,50);
        ramka.add(powrot);
        naglowek.add(dodaj);
        
        
        
        
        //ramka.add(powrot);
        ramka.setSize(600,600);
        // stała szerokość okna aby nam dzięcioły nie psuły aplikacji :-)
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setVisible(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setLocationRelativeTo(null);
    }
}
