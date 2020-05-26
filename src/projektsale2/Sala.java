/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Sala implements Rezerwowalny, Comparable<Sala>{
    public enum Właściwość { komputerowa, aula, ćwiczeniowa}

    private String nazwa;
    private int id;
    public int idSali;
    public int idBud;
    private Właściwość typ;
    private int pojemność;
    private ArrayList<Rezerwacja> listaRezerwacji = new ArrayList<Rezerwacja>();;
    private int liczbaRezerwacji;
    private static int kolejność_sal = 0;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa.length() == 0)
            {
            try {
                throw new KonstruktorException("Brak nazwy sali");
            } catch (KonstruktorException ex) {
                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Właściwość getTyp() {
        return typ;
    }

    public void setTyp(Właściwość typ) {
        this.typ = typ;
    }

    public int getPojemność() {
        return pojemność;
    }

    public void setPojemność(int pojemność) {
        this.pojemność = pojemność;
    }

    public ArrayList<Rezerwacja> getListaRezerwacji() {
        return listaRezerwacji;
    }

    public void setListaRezerwacji(ArrayList<Rezerwacja> listaRezerwacji) {
        this.listaRezerwacji = listaRezerwacji;
    }

    public int getLiczbaRezerwacji() {
        return liczbaRezerwacji;
    }

    public void setLiczbaRezerwacji(int liczbaRezerwacji) {
        this.liczbaRezerwacji = liczbaRezerwacji;
    }

    public static int getKolejność_sal() {
        return kolejność_sal;
    }

    public static void setKolejność_sal(int kolejność_sal) {
        Sala.kolejność_sal = kolejność_sal;
    }

    public Sala() {
        id = -1; // Wartość odróżnia błędną sale
    }
    
    public Sala(String nazwa, Właściwość typ, int pojemność)
    {
        this.idBud = 0;
        this.nazwa = nazwa;
        this.typ = typ;
        this.pojemność = pojemność;
        this.liczbaRezerwacji = 0;
        this.id = ++kolejność_sal;
    }
    
    public Sala(String nazwa, int typS, int pojemność)
    {
        this.nazwa = nazwa;
        if(typS ==1){
            this.typ = Właściwość.komputerowa;
        }
        else if(typS == 2){
            this.typ = Właściwość.aula;
        }
        else{
            this.typ = Właściwość.ćwiczeniowa;
        }
        
        this.pojemność = pojemność;
        this.liczbaRezerwacji = 0;
        this.id = ++kolejność_sal;
    }
    
    public Sala(String nazwa, String typS, int pojemność)
    {
        this.nazwa = nazwa;
        if(typS.equals("komputerowa")){
            this.typ = Właściwość.komputerowa;
        }
        else if(typS.equals("aula")){
            this.typ = Właściwość.aula;
        }
        else{
            this.typ = Właściwość.ćwiczeniowa;
        }
        
        this.pojemność = pojemność;
        this.liczbaRezerwacji = 0;
        this.id = ++kolejność_sal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("Nazwa: " + nazwa + " (Typ: " + typ + " Pojemność: " + pojemność+")");
            return sb.toString();
    }
    
    public boolean SprawdźRezerwację(Rezerwacja r)
    {
        //if(r.Numer == -1)
        //{
        //    return false; // rezerwacja została stworzona pustym konstruktorem więc nie można jej dodać
        //}
        for(var item : listaRezerwacji)
        {
            if (item.getDzień().getTime() == r.getDzień().getTime())
            {
                if ((r.getGodz_pocz().getTime() >= item.getGodz_pocz().getTime() && r.getGodz_pocz().getTime() < item.getGodzina_końcowa().getTime()))
                {
                    return false;
                } 
                else if (r.getGodzina_końcowa().getTime() > item.getGodz_pocz().getTime() && r.getGodzina_końcowa().getTime() <= item.getGodzina_końcowa().getTime())
                {                 
                    return false;
                }
                else if (r.getGodz_pocz().getTime() < item.getGodz_pocz().getTime() && r.getGodzina_końcowa().getTime() > item.getGodzina_końcowa().getTime())
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void DodajRezerwację(Rezerwacja r)
    {
        if (SprawdźRezerwację(r))
        {
            listaRezerwacji.add(r);
            liczbaRezerwacji++;
            r.setNumer(Rezerwacja.getKolejność_rezerwacji()+1);    //NIE działa takie nadawanie numerów rezerwacji
            //r.setNumer(++Rezerwacja.getKolejność_rezerwacji());    
            //r.numer = ++Rezerwacja.kolejność_rezerwacji;
            Sortuj();
            r.idRezerwacji = listaRezerwacji.size();
            r.idSali = this.idSali;
        }
        else
        {
            System.out.println("Sala zajęta");
        }
    }
    
    public void DodajRezerwację(Rezerwacja r, int nr)
    {
        if (SprawdźRezerwację(r))
        {
            listaRezerwacji.add(r);
            liczbaRezerwacji++;
            r.setNumer(nr);
            //if(nr!=0)
            if (Rezerwacja.getKolejność_rezerwacji() < nr)
            {
                Rezerwacja.setKolejność_rezerwacji(nr);
            }          
            Sortuj();
            r.idRezerwacji = listaRezerwacji.size();
        }
        else
        {
            System.out.println("Sala zajęta");
        }
    }
    
    public void UsuńRezerwację(Rezerwacja r, String pesel)
    {
        if (r.getNajem().getPesel() == pesel)
        {
            listaRezerwacji.remove(r);
            liczbaRezerwacji--;
        }
        else
        {
            System.out.println("Błędny PESEL!!!");
        }
    }
    
    
    public Rezerwacja ZnajdźRezerwację(int numer)
    {
        for(Rezerwacja r : listaRezerwacji){
            if(r.getNumer() == numer){
                return r;
            }
            else{
                System.out.println("Nie ma takiej rezerwacji");
            }
        }
        return null;
        //return listaRezerwacji.Find(x => x.Numer == numer);
    }

    public void Sortuj() {
        Collections.sort(listaRezerwacji);
    }

    
    public Rezerwacja ZnajdźRezerwację(Date dzień1, String godzina_początkowa, String godzina_końcowa) {
        try {
            SimpleDateFormat GodzPocz = new SimpleDateFormat("H:mm");
            Date godz_pocz = GodzPocz.parse(godzina_początkowa);
            SimpleDateFormat GodzKonc = new SimpleDateFormat("H:mm");
            Date godzina_końc = GodzKonc.parse(godzina_końcowa);
            for(Rezerwacja r : listaRezerwacji){
                if(r.getDzień()==dzień1 && r.getGodz_pocz() == godz_pocz && r.getGodzina_końcowa() == godzina_końc){
                    return r;
                }
            }        
        } catch (ParseException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int compareTo(Sala o) {
        return nazwa.compareTo(o.nazwa);
    }
    
    
    
    
}

