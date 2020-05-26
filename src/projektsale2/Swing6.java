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
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author Magda
 */
public class Swing6 {
    Swing6(Zbior z1){
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
        naglowek.setText("<html><p>Budynki:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><hr/>"
                
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
         JButton dodaj = new JButton("Dodaj");
        dodaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing5(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        dodaj.setBounds(250,50,150,50);
        
        String[] rekord = new String[20];
       int i = 0;
       for (Budynek b : z1.getSpisBudynkow()) {
            rekord[i] = "<html>"+b.toString()+"<br>"+"</html>";
            i++;
       }
        
        
        JList list = new JList(rekord); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        //list.setBounds(230, 160 , 190, 80);
        //list.setBackground(Color.GRAY);
        //list.setBorder(BorderFactory.createLineBorder(Color.black));
//      ...
        JScrollPane listScroller = new JScrollPane(list);
        //listScroller.setPreferredSize(new Dimension(80, 10));
        listScroller.setBounds(26, 75, 400, 300);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        naglowek.add(listScroller);
        ramka.add(powrot);
        ramka.add(dodaj);
        
        ramka.setSize(600,600);
        // stała szerokość okna aby nam dzięcioły nie psuły aplikacji :-)
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setVisible(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setLocationRelativeTo(null);
    }
}

