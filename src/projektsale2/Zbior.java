/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Zbior {
    private ArrayList<Budynek> spisBudynkow = new ArrayList<Budynek>();
    private ArrayList<Najemca> listaNajemcow = new ArrayList<Najemca>();
    private int rezerwacjeRazem;
    private int numer;

    public ArrayList<Budynek> getSpisBudynkow() {
        return spisBudynkow;
    }

    public void setSpisBudynkow(ArrayList<Budynek> spisBudynkow) {
        this.spisBudynkow = spisBudynkow;
    }

    public ArrayList<Najemca> getListaNajemcow() {
        return listaNajemcow;
    }

    public void setListaNajemcow(ArrayList<Najemca> listaNajemcow) {
        this.listaNajemcow = listaNajemcow;
    }

    public int getRezerwacjeRazem() {
        return rezerwacjeRazem;
    }

    public void setRezerwacjeRazem(int rezerwacjeRazem) {
        this.rezerwacjeRazem = rezerwacjeRazem;
    }
    
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }
    
    public Zbior()
    {
        listaNajemcow = new ArrayList<Najemca>();
        spisBudynkow = new ArrayList<Budynek>();
        numer = 0;
        rezerwacjeRazem = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA budynków: ").append('\n');
        for(Budynek b : spisBudynkow)
        {     
            sb.append("Nazwa budynku: " + b.getNazwa()).append('\n');
            sb.append("Udogodnienia: ").append('\n');
            sb.append("Wi-Fi: " + b.isWifi() + '\n' + "Winda: " + b.isWinda() + '\n' + "Gastronomia: " + b.isGastronomia() + '\n');
            sb.append("Lista sal: " + '\n');
            for(Sala s : b.getListaSal())
            {
                sb.append(s.getNazwa() + '\n');
                sb.append(s.getTyp()).append('\n');
                sb.append(s.getPojemność()).append('\n');
            }
        }
        sb.append("Spis Najemców: "+'\n');
        for(Najemca n : listaNajemcow)
        {
            sb.append(n).append('\n');
        }
        return sb.toString();
    }
    
    public void DodajBudynek(Budynek b)
    {
        spisBudynkow.add(b);
        b.IDbud = spisBudynkow.size();
    }
    
    public void UsunBudynek(Budynek b)
    {
        spisBudynkow.remove(b);
    }
    
    public void DodajNajemce(Najemca n)
    {
        listaNajemcow.add(n);
        n.idNajemcy = listaNajemcow.size();
    }
    
    public void UsunNajemce(Najemca n)
    {
        listaNajemcow.remove(n);
    }
    public String wypiszNajemcow (int i){
        
        return (listaNajemcow.get(i).getImię().toString()+" "+listaNajemcow.get(i).getNazwisko().toString());
    }
    public String WypiszSale(int i)
    {
        Budynek b = spisBudynkow.get(i);
        StringBuilder sb = new StringBuilder();     
        for(Sala s : b.getListaSal())
        {
            sb.append(s.getNazwa() +" ( Typ: "+s.getTyp()+", Pojemność: "+s.getPojemność()+" )"+ '\n');        
        }
        return sb.toString();
    }
}
