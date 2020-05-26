/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

import java.util.Arrays;

/**
 *
 * @author Admin
 */
abstract public class Dodatki {
    static private char[] stringZnaki = new char[] { 'ą', 'Ć', 'ć', 'ę', 'Ł', 'ł', 'Ń',
                'ń', 'Ó', 'ó', 'Ś', 'ś','Ś','Ź', 'ź','Ż','ż' };

    /**
     * @return the stringZnaki
     */
    public static char[] getStringZnaki() {
        return stringZnaki;
    }

    /**
     * @param aStringZnaki the stringZnaki to set
     */
    public static void setStringZnaki(char[] aStringZnaki) {
        stringZnaki = aStringZnaki;
    }
    
    
    public boolean SprawdźString(String str, char[] arr)// nadaje się jako funkcja do klasy abstrakcyjnej
        {
            if (str.length() <= 1) return false;
            else
            {
                for(var item : str.toCharArray())
                {
                    if (Arrays.asList(arr).contains(item) || (item >= 65 && item <= 90) || (item >= 97 && item <= 122))
                    {
                        // nic nie robi
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    
}
