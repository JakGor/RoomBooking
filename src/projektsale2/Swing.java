package projektsale2;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.sql.*;

/**
 *
 * @author
 */
public class Swing implements WindowListener {
    static String[][] daneDoBazy = new String[10][5];
    String customInsert = "";
    String customInsertBudynek = "";
    String customInsertSala = "";
    String customInsertRezerwacja = "";
    String polaczenieURL = "jdbc:mysql://localhost/rezerwacje?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=";
    Connection conn = null;
    Swing(Zbior z1){
        /***************************************/
        final String header = "<html><p>Witaj w aplikacji do rezerwacji sal!</p><hr/><br/>";
        final String messageMain = header + "<p>Najedź na przycisk menu znajdującego się po lewej stronie,"
                + " aby zobaczyć szczegółowy opis każdej z zakładek.</p></html>";
        final String message1 = header + "<p>Zarezerwuj swoją salę już teraz,"
                + " sprawdź dostępne terminy lub usuń swoją rezerwację.</p></html>";
        final String message2 = header + "<p>Dodanie najemcy, jest niezbędne do&nbsp;rezerwacji"
                + " sali, konieczne może być wypełnienie krótkiego formularza.</p></html>";
        final String message3 = header + "<p>Dodaj salę wraz z informacją o jej maksymalnej liczbie"
                + " miejsc. Sprawdzić listę wszystkich sal w danym "
                + "budynku.</p></html>";
        final String message4 = header + "<p>Zarządzaj budynkami, które przechowują informację "
                + "o salach. Możesz dodać, usunąć lub sprawdzić budynek.</p></html>";
        final String message5 = header + "<p>Zakończenie pracy z programem.</p></html>";
        final int buttonHeight = 50;
        final int buttonWidth = 200;
        final int buttonMargin = 15;
        final int buttonSpaceH = 115;
        final int buttonSpaceW = 50;
        final int labelWidth = 250;
        final int labelHeight = 310;
        /***************************************/
        //Stworzenie i ustawienie tła ramki
        BufferedImage myImage = null;
        try{
            myImage = ImageIO.read(new File("images\\img2.jpg"));
        } catch(IOException e) {
        }
        JFrame ramka = new JFrame("Strona główna");
        ramka.setContentPane(new ImagePanel(myImage));
        /***************************************/
        //Tworzenie label z zmiennymi napisami 
        JLabel img = new JLabel();
        img.setBorder(
            new CompoundBorder(
                new CompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY,10),
                    BorderFactory.createLineBorder(Color.GRAY,6)     
                ),
            BorderFactory.createLineBorder(Color.WHITE,10)
            )
        );
        img.setFont(new Font("Serif", Font.PLAIN, 21));
        img.setVerticalAlignment(SwingConstants.TOP);
        img.setOpaque(true);
        img.setBackground(Color.WHITE);
        img.setText(messageMain);
        img.setBounds(2*buttonSpaceW + buttonWidth + 10,
                buttonSpaceH
                ,labelWidth,labelHeight);
        ramka.add(img);
        /************************/
        //Tworzenie przycisków
        JButton b[] = new JButton[5];
        
        b[0] = new JButton("Rezerwacje");
        b[0].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                img.setText(message1);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
               img.setText(messageMain);
            }
            
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing2(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        ramka.add(b[0]);
        /************************/
        b[1] = new JButton("Najemcy");
        b[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                img.setText(message2);
            }
            
            public void mouseExited(java.awt.event.MouseEvent event){
                img.setText(messageMain);
            }
            
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing3(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        ramka.add(b[1]);
        /************************/
        b[2] = new JButton("Sale");
        b[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                img.setText(message3);
            }
            
            public void mouseExited(java.awt.event.MouseEvent event){
                img.setText(messageMain);
            }
            
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing4(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        ramka.add(b[2]);
        /************************/
        b[3] = new JButton("Budynki");
        b[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                img.setText(message4);
            }
            
            public void mouseExited(java.awt.event.MouseEvent event){
                img.setText(messageMain);
            }
            
            public void mouseClicked(java.awt.event.MouseEvent event){
                new Swing6(z1);
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        ramka.add(b[3]);
        /************************/
        b[4] = new JButton("Koniec");
        b[4].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                img.setText(message5);
            }
            
            public void mouseExited(java.awt.event.MouseEvent event){
                img.setText(messageMain);
            }
            
            public void mouseClicked(java.awt.event.MouseEvent event){
                ramka.setVisible(false);
                ramka.dispose();
            }
        });
        ramka.add(b[4]);
        /************************/
        // ustawienie pozycji przycisków
        int length = b.length;
        for (int i = 0; i < length ;++i) {
          b[i].setBounds(buttonSpaceW,
                  buttonSpaceH + buttonHeight*i + buttonMargin*i
                  ,buttonWidth,buttonHeight);  
        }
        /************************/
        //Ustawienia ramki 
        ramka.setSize(600,600);
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setVisible(true);
        ramka.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {

         @Override
             public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
             null, "Are You Sure to Close Application?", 
             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    
                    try {
 
                        //Ustawiamy dane dotyczące podłączenia
                        conn = DriverManager.getConnection(polaczenieURL);
                       
                        //Ustawiamy sterownik MySQL
                        Class.forName("com.mysql.cj.jdbc.Driver");
                       
                        //Uruchamiamy zapytanie do bazy danych
                        Statement st = conn.createStatement();
                        int i = 0;
                        
                        for (Najemca n : z1.getListaNajemcow()) {
                            String[] rekord = new String[5];
                            rekord[0] = Integer.toString(i+1);
                            rekord[1] = n.getImię().toString();
                            rekord[2] = n.getNazwisko().toString();
                            rekord[3] = n.getPesel().toString();
                            rekord[3] = n.getPłeć().toString();
                            customInsert+=("("+Integer.toString(i+1)+",'"+n.getImię().toString()+"','"+
                            n.getNazwisko().toString()+"','"+n.getPesel().toString()+"','"+n.getPłeć().toString()+"'),");
                            daneDoBazy[i] =rekord;
                            i++;
                        }
                        
//                        st.executeUpdate("INSERT INTO najemcy VALUES (2,'Fred', 'Flinstone', '00000000000', 'M')"+
//                         ";");
                        st.executeUpdate("DELETE FROM rezerwacje;");
                        st.executeUpdate("DELETE FROM najemcy;");
                        st.executeUpdate("INSERT INTO najemcy VALUES "+customInsert.substring(0, customInsert.length()-1) + ";");
                         System.out.println("INSERT NAJEMCY: "+customInsert.substring(0, customInsert.length()-1));  
                         i = 0;
                        for (Budynek b: z1.getSpisBudynkow()) {
//                            String[] rekord = new String[5];
//                            rekord[0] = Integer.toString(i+1);
//                            rekord[1] = b.getNazwa().toString();
//                            rekord[2] = b.getWinda();
//                            rekord[3] = b.getGastro();
//                            rekord[3] = b.getWiFi();
                            customInsertBudynek+=("("+Integer.toString(i+1)+",'"+b.getNazwa().toString()+"','"+
                            b.getWinda()+"','"+b.getGastro()+"','"+b.getWiFi()+"'),");
//                            daneDoBazy[i] =rekord;
                            i++;
                        }
                        st.executeUpdate("DELETE FROM sale;");
                        st.executeUpdate("DELETE FROM budynki;");
                        st.executeUpdate("INSERT INTO budynki VALUES "+customInsertBudynek.substring(0, customInsertBudynek.length()-1) + ";");
                         System.out.println("INSERT BUDYNKI: "+customInsertBudynek.substring(0, customInsertBudynek.length()-1));  
                         i = 0;
                         int typInt = 0;
                        for (Budynek b: z1.getSpisBudynkow()) {
                            if(b.getListaSal().size()>0){
                            for (Sala s: b.getListaSal()){
                                //typInt = 0;
                               
                                System.out.println(s.getTyp().toString());
                                customInsertSala+=("("+Integer.toString(i+1)+",'"+s.getNazwa().toString()+"','"+s.getTyp().toString()
                            +"',"+Integer.toString(s.getPojemność())+","+Integer.toString(s.idBud)+"),");
                            //System.out.println(customInsertSala);
                            //System.out.println(customInsertSala.substring(0, customInsertSala.length()-1));
                         //System.out.println(customInsertSala);
                            
                            i++;
                        }

                            }
       
                        
                }
                       System.out.println("INSERT SALE: "+customInsertSala.substring(0, customInsertSala.length()-1));
                       st.executeUpdate("INSERT INTO sale VALUES "+customInsertSala.substring(0, customInsertSala.length()-1) + ";");
                       i=0; 
                       for (Budynek b: z1.getSpisBudynkow()) {
                            if(b.getListaSal().size()>0){
                            for (Sala s: b.getListaSal()){
                                if(s.getListaRezerwacji().size()>0){
                                    for(Rezerwacja r: s.getListaRezerwacji()){
                                       customInsertRezerwacja+="("+Integer.toString(i+1)+", "+Integer.toString(r.getNajem().idNajemcy)+", "+ Integer.toString(s.idSali)+", '"+
                                               r.getDzienSTR()+"', '"+r.getGodzPoczSTR()+"', '"+r.getGodzKoncSTR()+"'),";
                                          
                            //System.out.println(customInsertRezerwacja);
                            System.out.println(i +"_____________"+customInsertRezerwacja.substring(0, customInsertRezerwacja.length()-1));
                            i++; 
                                    }
                                    

                                }
                            }
                            }
                        }
                       System.out.println("INSERT REZERWACJA: "+customInsertRezerwacja);
                        st.executeUpdate("INSERT INTO rezerwacje VALUES "+customInsertRezerwacja.substring(0, customInsertRezerwacja.length()-1) + ";");
                        conn.close();
                    }
                //Wyrzuć wyjątki jężeli nastąpią błędy z podłączeniem do bazy danych lub blędy zapytania o dane
                catch(ClassNotFoundException wyjatek) {
                        System.out.println("Problem ze sterownikiem");
                }
 
                catch(SQLException wyjatek) {
                        //e.printStackTrace();
                        //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
                        System.out.println("SQLException: " + wyjatek.getMessage());
                    System.out.println("SQLState: " + wyjatek.getSQLState());
                    System.out.println("VendorError: " + wyjatek.getErrorCode());
                }
            System.exit(0);
        }
    }
};
        ramka.addWindowListener(  exitListener);
        ramka.setLocationRelativeTo(null);
    }
    
     public void windowClosing(WindowEvent arg0) {
    System.exit(0);
  }

  public void windowOpened(WindowEvent arg0) {}
  public void windowClosed(WindowEvent arg0) {}
  public void windowIconified(WindowEvent arg0) {}
  public void windowDeiconified(WindowEvent arg0) {}
  public void windowActivated(WindowEvent arg0) {}
  public void windowDeactivated(WindowEvent arg0) {}
//    public void zamknij (){
//        JFrame.EXIT_ON_CLOSE;
//    }
}
