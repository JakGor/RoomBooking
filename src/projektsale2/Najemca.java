/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
enum Płcie { K,M};
public class Najemca extends Dodatki {
    public int idNajemcy;
    private String imię; 
    private String nazwisko;
    private String pesel;
    private Płcie płeć;

    /**
     * @return the imię
     */
    public String getImię() {
        return imię;
    }

    /**
     * @param imię the imię to set
     */
    public void setImię(String imię) {
        if (SprawdźString(imię, Dodatki.getStringZnaki())){
            this.imię = imię.toUpperCase();// Wszystko na duże litery aby nie bawić się w rozróżnienia (można usunąć)
        }
        else{
            try {
                throw new KonstruktorException("Błędne imię najemcy");
            } catch (KonstruktorException ex) {
                Logger.getLogger(Najemca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }
    

    /**
     * @return the nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * @param nazwisko the nazwisko to set
     */
    public void setNazwisko(String nazwisko) {
        if (SprawdźString(nazwisko, Dodatki.getStringZnaki())){
            this.nazwisko = nazwisko.toUpperCase();// Wszystko na duże litery aby nie bawić się w rozróżnienia (można usunąć)
        }
        else{
            try {
                throw new KonstruktorException("Błędne nazwisko najemcy");
            } catch (KonstruktorException ex) {
                Logger.getLogger(Najemca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }       

    /**
     * @return the pesel
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * @param pesel the pesel to set
     */
    public void setPesel(String pesel) throws KonstruktorException {  
        if (sprawdzPESEL(pesel)==false)
        {
            throw new KonstruktorException("Niepoprawny PESEL");
        }
        else{
            this.pesel = pesel;
        }
    }

    /**
     * @return the płeć
     */
    public Płcie getPłeć() {
        return płeć;
    }

    /**
     * @param płeć the płeć to set
     */
    public void setPłeć(Płcie płeć) {
        this.płeć = płeć;
    }
    public void setPłeć(String płeć) {
        if(płeć.equals("M")){
            this.płeć = Płcie.M;
        }else{
            this.płeć= Płcie.K;
        }
    }
    
    public Najemca(){}
    
    public Najemca(String imię, String nazwisko, String pesel, String płeć) throws KonstruktorException
    {
        this.setImię(imię);
        this.setNazwisko(nazwisko);
        this.setPesel(pesel);
         if(płeć.equals("M")){
            this.płeć = Płcie.M;
        }else{
            this.płeć= Płcie.K;
        }
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Najemca:"+"\n");
        sb.append("Imię: ").append(this.imię+"\n");
        sb.append("Nazwisko: ").append(this.nazwisko+"\n");
        sb.append("PESEL: ").append(this.pesel+"\n");
        sb.append("Płeć: ").append(this.płeć.toString()+"\n");
        return sb.toString();
    }
    
    
    
    public static boolean sprawdzPESEL(String PESEL)
        {

            int[] wagi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            int sumaKontrolna = 0;
            ///sprawdzanie czy każdy element z numeru PESEL jest liczbą oraz czy długość wynosi 11 cyfr
            for (int i = 0; i < PESEL.length() - 1; i++)
            {
                if (isNumeric(PESEL.charAt(i)) == false || PESEL.length() != 11)
                {
                    return false;
                }
            }
            //Console.WriteLine("Pesel jest z cyfr i ma długość OK");                

            ///obliczanie sumy kontrolnej (waga * kolejne cyfry z PESELu)
            sumaKontrolna = Character.getNumericValue(PESEL.charAt(0)) * wagi[0] +
                Character.getNumericValue(PESEL.charAt(1)) * wagi[1] +
                Character.getNumericValue(PESEL.charAt(2)) * wagi[2] +
                Character.getNumericValue(PESEL.charAt(3)) * wagi[3] +
                Character.getNumericValue(PESEL.charAt(4)) * wagi[4] +
                Character.getNumericValue(PESEL.charAt(5)) * wagi[5] +
                Character.getNumericValue(PESEL.charAt(6)) * wagi[6] +
                Character.getNumericValue(PESEL.charAt(7)) * wagi[7] +
                Character.getNumericValue(PESEL.charAt(8)) * wagi[8] +
                Character.getNumericValue(PESEL.charAt(9)) * wagi[9];
            
            sumaKontrolna %= 10;
            sumaKontrolna = 10 - sumaKontrolna;
            sumaKontrolna %= 10;
            
            
            ///sprawdzenie czy suma kontrolna zgadza się z ostatnią cyfrą PESELu
            if(sumaKontrolna == Character.getNumericValue(PESEL.charAt(10)))
            {
                return true;
            }
            else
                return false;


        }

    
    
    //ta funkcja sprawdza czy podany char jest liczbą (potrzebna mi była do peselu)
    public static boolean isNumeric(char strNum) {
        String nowy = Character.toString(strNum);
        try {
            double d = Double.parseDouble(nowy);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
}

