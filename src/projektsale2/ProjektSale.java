package projektsale2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektsale2.Sala.Właściwość;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ProjektSale {

     static String[] daneZBazy = new String[10];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws KonstruktorException {
       String[] tmpRecord = new String[10];  
      String polaczenieURL = "jdbc:mysql://localhost/rezerwacje?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=";
      Connection conn = null;  
      String query = "SELECT * FROM najemcy";
      String query2 = "SELECT * FROM budynki";
      String query3 = "SELECT * FROM sale";
      String query4 = "SELECT * FROM rezerwacje";
      Najemca n1 = new Najemca("Dawid","Jobs","98052408891",Płcie.M.toString());  
      Najemca n2 = new Najemca("Steve","Jobs","98052408891",Płcie.M.toString()); 
     
      
      Zbior z1 = new Zbior();
      //z1.DodajNajemce(n1);
      //z1.DodajNajemce(n2);
      Sala s1 = new Sala("114", Właściwość.ćwiczeniowa, 32);
      Budynek b1 = new Budynek("D14", true, true, true);
      b1.DodajSale(s1);
      //z1.DodajBudynek(b1);
       try {
 
                        //Ustawiamy dane dotyczące podłączenia
                        conn = DriverManager.getConnection(polaczenieURL);
                       
                        //Ustawiamy sterownik MySQL
                        Class.forName("com.mysql.cj.jdbc.Driver");
                       
                        //Uruchamiamy zapytanie do bazy danych
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        
       
                        
                        while (rs.next()) {
                                tmpRecord = wyswietlDaneZBazy(rs);
                                Najemca tmpNajemca = new Najemca(tmpRecord[0],tmpRecord[1],tmpRecord[2],tmpRecord[3]);
                                tmpNajemca.idNajemcy = rs.getInt(1);
                                z1.DodajNajemce(tmpNajemca);
                        }
                        rs = st.executeQuery(query2);
                        int j = 1;
                        while (rs.next()) {
                                Budynek tmpBudynek = new Budynek(rs.getString(2),rs.getBoolean(3),rs.getBoolean(4),rs.getBoolean(5));
                                tmpBudynek.IDbud = rs.getInt(1);
                                z1.DodajBudynek(tmpBudynek);
                                j++;
                                
                        }
                        rs = st.executeQuery(query3);
                        while (rs.next()) {
                                tmpRecord = wyswietlDaneZBazy(rs);
                                for(Budynek b:  z1.getSpisBudynkow()){
                                    if(b.IDbud == rs.getInt(5)){
                                       Sala tmpSala = new Sala(rs.getString(2),rs.getString(3),rs.getInt(4));
                                       tmpSala.idBud = rs.getInt(5);
                                       tmpSala.idSali = rs.getInt(1);
                                        b.DodajSale(tmpSala);
                                        b.getListaSal().get(b.getListaSal().size()-1).idSali = rs.getInt(1);

                                        System.out.println("___SALA W BUD_____"+ tmpSala.idBud+"__"+
                                                tmpSala.getNazwa().toString()+"________-_____t"+tmpSala.getTyp().toString());
                                    }
                                        
                                }
                                
                                //z1.DodajBudynek(new Budynek(rs.getString(2),rs.getBoolean(3),rs.getBoolean(4),rs.getBoolean(5)));
                                
                        }
                        
                        
                        rs = st.executeQuery(query4);
                        while(rs.next()){
                            tmpRecord = wyswietlDaneZBazyBudynek(rs);
                            for(Budynek b:  z1.getSpisBudynkow()){
                                for(Sala s: b.getListaSal()){
                                    if(s.idSali == rs.getInt(3)){
                                        
                                        Rezerwacja tmpRezerwacja = new Rezerwacja(z1.getListaNajemcow().get(rs.getInt(2)-1),rs.getString(4),rs.getString(5),rs.getString(6));
                                        tmpRezerwacja.idRezerwacji = rs.getInt(1);
                                        tmpRezerwacja.idSali = rs.getInt(3);
                                        s.DodajRezerwację(tmpRezerwacja);
                                        System.out.println(Integer.toString(tmpRezerwacja.idRezerwacji)+ b.getNazwa()+"____nr sali:_____"+Integer.toString(tmpRezerwacja.idSali)
                                            +"__________"    + tmpRezerwacja);
                                    }
                                }
                            }
                        }
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
      new Swing(z1);
    }
    static String[] wyswietlDaneZBazy(ResultSet rs){
                try{
                    
                System.out.println("WYSWIETL DANE Z BAZY \n NR: "+rs.getInt(1)+"\n");
                daneZBazy[0] = rs.getString(2);
                daneZBazy[1] = rs.getString(3);
                daneZBazy[2] = rs.getString(4);
                daneZBazy[3] = rs.getString(5);
                
                System.out.println("\n" + daneZBazy[0] + " ");
                 System.out.println("\n" + daneZBazy[1] + " ");
                 System.out.println("\n" + daneZBazy[2] + " ");
                  System.out.println("\n" + daneZBazy[3] + " ");
                 
                 
                }
                catch(SQLException e) {
                        e.printStackTrace();
                }
                 return daneZBazy;
    
    
    }
        static String[] wyswietlDaneZBazyBudynek(ResultSet rs){
                try{
                    
                
                daneZBazy[0] = rs.getString(2);
                daneZBazy[1] = rs.getString(3);
                daneZBazy[2] = rs.getString(4);
                daneZBazy[3] = rs.getString(5);
                daneZBazy[4] = rs.getString(6);
                
                System.out.println("\n" + daneZBazy[0] + " ");
                 System.out.println("\n" + daneZBazy[1] + " ");
                 System.out.println("\n" + daneZBazy[2] + " ");
                  System.out.println("\n" + daneZBazy[3] + " ");
                 System.out.println("\n" + daneZBazy[4] + " ");
                 
                }
                catch(SQLException e) {
                        e.printStackTrace();
                }
                 return daneZBazy;
        }
    
    
}
