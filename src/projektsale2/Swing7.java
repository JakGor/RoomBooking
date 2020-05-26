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
public class Swing7 {
    Swing7(Zbior z1){
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

