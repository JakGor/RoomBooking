/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
class Rezerwacja implements Comparable<Rezerwacja>, Cloneable{
    private int numer; // -1 oznacza pusty konstruktor, 0 oznacza że nie ma na liście rezerwacji, liczba naturalna jest na liście
    private Najemca najem;
    private Date dzień;
    private Date godz_pocz;
    private Date godzina_końcowa;
    private static int kolejność_rezerwacji = 0;
    private String hourPocz;
    private String hourKonc;
    private String Day;
    public int idSali;
    public int idRezerwacji;

    
    public String getDzienSTR(){
    return Day;
}
 public String getGodzPoczSTR(){
    return hourPocz;
}
  public String getGodzKoncSTR(){
    return hourKonc;
}
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public Najemca getNajem() {
        return najem;
    }

    public void setNajem(Najemca najem) {
        if (najem.getPesel().charAt(0) == '0')
        {
            try {
                throw new KonstruktorException("Rezerwacja utworzona njemcą utworzonym pustym konstruktorem");
            } catch (KonstruktorException ex) {
                Logger.getLogger(Rezerwacja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.najem = najem;
    }

    public Date getDzień() {
        return dzień;
    }

    public void setDzień(Date dzień) {
        if (dzień.getTime() < System.currentTimeMillis())
        {
            //throw new KonstruktorException("Zła data w rezerwacji");
        }
        else
        {
            this.dzień = dzień;
        }
        
    }

    public Date getGodz_pocz() {
        return godz_pocz;
    }

    public void setGodz_pocz(Date godz_pocz) {
        this.godz_pocz = godz_pocz;
    }

    public Date getGodzina_końcowa() {
        return godzina_końcowa;
    }

    public void setGodzina_końcowa(Date godzina_końcowa) {
        this.godzina_końcowa = godzina_końcowa;
    }

    public static int getKolejność_rezerwacji() {
        return kolejność_rezerwacji;
    }

    public static void setKolejność_rezerwacji(int kolejność_rezerwacji) {
        Rezerwacja.kolejność_rezerwacji = kolejność_rezerwacji;
    }

    public Rezerwacja()
    {
        numer = -1;
        najem = new Najemca();
    }
    
    public Rezerwacja(Najemca najemca, String dzien, String godzina_początkowa, String godzina_końc)
        {
            numer = 0;
            najem = najemca;
        
            
        try {
            //Date newDay;
            //Date.TryParseExact(dzień, new[] { "yyyy-MM-dd", "yyyy/MM/dd", "MM/dd/yy", "dd-MMM-yy", "dd/MM/yyyy", "dd-MM-yyyy" }, null, DateTimeStyles.None, out newDay);
            
            SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");          //TAKI MAMY FORMAT DATY!!!!
            dzień = day.parse(dzien);
            SimpleDateFormat newDay = new SimpleDateFormat("dd/MM/yyyy");
            Day = newDay.format(dzień);
            
            SimpleDateFormat GodzPocz = new SimpleDateFormat("H:mm");
            godz_pocz = GodzPocz.parse(godzina_początkowa);
            SimpleDateFormat newGodzPocz = new SimpleDateFormat("H:mm");
            hourPocz = newGodzPocz.format(godz_pocz);
            
            SimpleDateFormat GodzKonc = new SimpleDateFormat("H:mm");
            godzina_końcowa = GodzKonc.parse(godzina_końc);
            SimpleDateFormat newGodzKonc = new SimpleDateFormat("H:mm");
            hourKonc = newGodzKonc.format(godzina_końcowa);
            
            
            //DateTime nowaGodzinaPocz;
            /*if (!DateTime.TryParseExact(godzina_początkowa, "H:mm", null, DateTimeStyles.None, out nowaGodzinaPocz))
            {
            throw new KonstruktorException("Zły format godziny początkowej");
            }
            Godz_pocz = nowaGodzinaPocz.TimeOfDay;
            DateTime nowaGodzinaKoń;
            if (!DateTime.TryParseExact(godzina_końcowa, "H:mm", null, DateTimeStyles.None, out nowaGodzinaKoń))
            {
            throw new KonstruktorException("Zły format godziny końcowej");
            }
            Godzina_końcowa = nowaGodzinaKoń.TimeOfDay;
            if (Godzina_końcowa <= Godz_pocz)
            {
            throw new KonstruktorException("godzina początkowa nie może być większa od końcowej");
            }*/
        } catch (ParseException ex) {
            Logger.getLogger(Rezerwacja.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numer rezerwacji: ").append(numer).append('\n');
        sb.append(najem.toString()).append('\n');
        sb.append("Data: ").append(Day).append('\n');
        sb.append("Od godziny: ").append(hourPocz).append('\n');
        sb.append("Do godziny: ").append(hourKonc).append('\n');
        return sb.toString();
    }

    @Override
    public int compareTo(Rezerwacja r) {
        if (dzień.getTime() < r.getDzień().getTime()) //nie działa
        {
            return -1;
        }
        else if (dzień == r.getDzień())
        {
            return godz_pocz.compareTo(r.getGodz_pocz());
        }
        return 1; 
    }
    
    
    






}
