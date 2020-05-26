/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Budynek {
    private String nazwa;
    public int IDbud;
    private ArrayList<Sala> listaSal = new ArrayList<Sala>();;
    private boolean winda;
    private boolean gastronomia;
    private boolean wifi;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa.length() == 0)
        {
            try {
                throw new KonstruktorException("Brak nazwy budynku");
            } catch (KonstruktorException ex) {
                Logger.getLogger(Budynek.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.nazwa = nazwa;
    }

    public ArrayList<Sala> getListaSal() {
        return listaSal;
    }

    public void setListaSal(ArrayList<Sala> listaSal) {
        this.listaSal = listaSal;
    }

    public boolean isWinda() {
        return winda;
    }

    public void setWinda(boolean winda) {
        this.winda = winda;
    }

    public boolean isGastronomia() {
        return gastronomia;
    }

    public void setGastronomia(boolean gastronomia) {
        this.gastronomia = gastronomia;
    }

    public boolean isWifi() {
        return wifi;
    }
    public String getWiFi(){
        if(wifi == true){
            return "1";
        }
        else{
            return "0";
        }
    }
    public String getWinda(){
        if(winda == true){
            return "1";
        }
        else{
            return "0";
        }
    }
    public String getGastro(){
        if(gastronomia == true){
            return "1";
        }
        else{
            return "0";
        }
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
    
    
    public Budynek()
    {
        
    }
    
    public Budynek(String nazwa, boolean winda, boolean gastronomia, boolean wifi)
    {
        this.IDbud = -1;
        this.nazwa = nazwa;
        this.winda = winda;
        this.gastronomia = gastronomia;
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa budynku: " + nazwa + '\n' + "<br>" +"Udogodnienia: "+ '\n' +"<br>");
        sb.append("Wi-Fi: " + wifi + '\n' +"<br>"+ "Winda: " + winda + '\n' +"<br>"+ "Gastronomia: " + gastronomia + '\n'+"<br>");
        sb.append("Lista sal: " + '\n'+"<br>");
        for(Sala s : listaSal)
        {
            sb.append(s).append('\n'+"<br>");
        }
        return sb.toString();
    }
    
    public void DodajSale(Sala s)
    {
        if (listaSal.contains(s))
        {
            System.out.println("Taka sala już istnieje");
        }
        else
        {
            listaSal.add(s);
            s.setId(Sala.getKolejność_sal()+1);
            s.idSali = listaSal.size();
            //s.Id = ++Sala.Kolejność_sal;
            //Collections.sort(listaSal);           //  NIE WIEM CZEMU TU NIE POSORTUJE, A W KLASIE SALA SIĘ DA POSORTOWAĆ
            //listaSal.Sort();
        }
    }
    
    
    
    
}
