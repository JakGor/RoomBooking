/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public interface Rezerwowalny {
    void DodajRezerwację(Rezerwacja r);
    void UsuńRezerwację(Rezerwacja r, String pesel);
    Rezerwacja ZnajdźRezerwację(Date dzień1,String godzina_początkowa,String godzina_końcowa);  //ZMIENIŁEM Z ARRAYLIST<rEZERWACJA> NA SAMO rEZERWACJA, BO NIE MOŻE BYĆ DO JEDNEJ SALI W TYM SAMYM CZASIE WIECEJ  NIŻ JEDNEJ REZERWACJI
    Rezerwacja ZnajdźRezerwację(int numer);
}
