/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class Swing3 {
    private final static String newline = "\n";
    Swing3(Zbior z1){
        /***********************/
        //Ustawienie tła ramki
        BufferedImage myImage = null;
        try{
            myImage = ImageIO.read(new File("images\\img2.jpg"));
        } catch(IOException e) {
        }
        JFrame ramka = new JFrame("Rezerwacje");
        ramka.setContentPane(new ImagePanel(myImage));
        /***********************/
        // Tworzenie przycisku powrotu
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
        /***********************/
        //Formularz dodania najemcy
        JLabel naglowek = new JLabel();
        naglowek.setText("<html><p>Dodaj najemce&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><hr/>"
                + "<p>Imię:</p><p></p><p>Nazwisko:</p><p></p><p>Pesel:</p>"
                + "<p></p><p>Płeć:</p>"
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
        JTextField imie = new JTextField("Imię");
        imie.setBounds(26,95,190,25);
        imie.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                imie.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
            }
            public void focusLost(FocusEvent e) {
                imie.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        naglowek.add(imie);
        /***********************/
        JTextField nazwisko = new JTextField("Nazwisko");
        nazwisko.setBounds(26,150,190,25);
        nazwisko.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nazwisko.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
            }
            public void focusLost(FocusEvent e) {
                nazwisko.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        naglowek.add(nazwisko);
        /***********************/
        JTextField pesel = new JTextField("NR PESEL");
        pesel.setBounds(26,205,190,25);
        pesel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                pesel.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
            }
            public void focusLost(FocusEvent e) {
                pesel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
            }
        });
        naglowek.add(pesel);
        /***********************/
        String plcie[] = {"K","M"};
        JComboBox plec = new JComboBox(plcie);
        plec.setBounds(26,260,190,25);
        plec.setBackground(Color.WHITE);
        naglowek.add(plec);
        /***********************/
        
        
        JLabel etykietaTA = new JLabel("Lista Najemców");
        etykietaTA.setBounds(226, 20, 190, 30);
        naglowek.add(etykietaTA);
        JTextArea textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setBounds(230,55,190, 100);
        textArea.setEditable(false);
        for(int i=0 ; i<z1.getListaNajemcow().size();i++){
            textArea.append((z1.wypiszNajemcow(i)+ newline));
        }
        
        
        naglowek.add(textArea);
        
        
        
        
        
        
        /***********************/
        JButton submit = new JButton("Dodaj");
        submit.setBounds(26,300,190,25);
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event){
                Najemca tmp= new Najemca();
                try {
                    tmp =new Najemca(imie.getText(),nazwisko.getText(),pesel.getText(),
                            plec.getSelectedItem().toString());
                    z1.DodajNajemce(tmp);
                    
                } catch (KonstruktorException ex) {
                    Logger.getLogger(Swing3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        naglowek.add(submit);
       String[] rekord = new String[20];
       int i = 0;
       for (Najemca n : z1.getListaNajemcow()) {
            rekord[i] = n.getImię() +" "+n.getNazwisko();
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
        listScroller.setBounds(230, 160, 190, 80);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        naglowek.add(listScroller);
        
        
        /***********************/
        //Ustawienia ramki
        ramka.setSize(600,600);
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setVisible(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setLocationRelativeTo(null);
    }
}
