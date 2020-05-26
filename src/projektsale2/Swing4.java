/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
public class Swing4 {
    static final int FPS_MIN = 0;
        static final int FPS_MAX = 30;
        static final int FPS_INIT = 15;    //initial frames per second
    Swing4(Zbior z1){
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
        
        
        JLabel naglowek = new JLabel();
        naglowek.setText("<html><p>Dodaj salę&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><hr/>"
                + "<p>Budynek:</p><p></p><p>Nazwa/Numer:</p><p></p><p>Typ:</p>"
                + "<p></p><p>Pojemność:</p>"
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
        /***********************/
       String[] rekord = new String[z1.getSpisBudynkow().size()];
       int i = 0;
       for (Budynek b : z1.getSpisBudynkow()) {
            rekord[i] = b.getNazwa().toString();
            i++;
       }
       
        JComboBox nazwaBudynku = new JComboBox(rekord);
        nazwaBudynku.setBounds(26,95,190,25);
//        nazwaBudynku.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                nazwaBudynku.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//            }
//            public void focusLost(FocusEvent e) {
//                nazwaBudynku.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
//            }
//        });
        naglowek.add(nazwaBudynku);
        //////////////////////////////////////
        JTextField nazwaSali = new JTextField("Numer/nazwa");
        nazwaSali.setBounds(26,150,190,25);
        nazwaSali.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nazwaSali.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
            }
            public void focusLost(FocusEvent e) {
                nazwaSali.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        naglowek.add(nazwaSali);
        /***********************/
        
        /***********************/
        
        String typy[] = {"komputerowa","aula","ćwiczeniowa"};
        JComboBox typSali = new JComboBox(typy);
        typSali.setBounds(26,205,190,25);
        typSali.setBackground(Color.WHITE);
        naglowek.add(typSali);
        /***********************/
        

JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                                      FPS_MIN, FPS_MAX, FPS_INIT);


//Turn on labels at major tick marks.
//framesPerSecond.setMajorTickSpacing(10);
//framesPerSecond.setMinorTickSpacing(1);
//framesPerSecond.setPaintTicks(true);
//framesPerSecond.setPaintLabels(true);
//framesPerSecond.setBounds(26, 300,200,40);
//naglowek.add(framesPerSecond);
        JTextField pojemnosc = new JTextField("1");
        pojemnosc.setBounds(26,260,190,25);
        pojemnosc.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pojemnosc.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
            }
            public void focusLost(FocusEvent e) {
                pojemnosc.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        naglowek.add(pojemnosc);
          JButton dodaj = new JButton("Dodaj");
        
        dodaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                
                Sala tmp = new Sala();
                tmp = new Sala(nazwaSali.getText(), typSali.getSelectedItem().toString(),Integer.parseInt(pojemnosc.getText()));
                System.out.println(tmp.toString());
                for (Budynek b : z1.getSpisBudynkow()) {
                    if(b.getNazwa()== nazwaBudynku.getSelectedItem().toString()){
                        b.DodajSale(tmp);
                        tmp.idBud = b.IDbud;
                    }
                        }
                
                new Swing(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        dodaj.setBounds(26,300,150,50);
        naglowek.add(dodaj);
        /***********************/
        ramka.add(powrot);
        ramka.setSize(600,600);
        // stała szerokość okna aby nam dzięcioły nie psuły aplikacji :-)
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setVisible(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setLocationRelativeTo(null);
    }
}
