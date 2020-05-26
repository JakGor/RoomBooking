/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektsale2;

/**
 *
 * @author Admin
 */
public class KonstruktorException extends Exception{
    private String str;
    
    public KonstruktorException(){
        str = "Błędny parametr konstruktora";
    }
    
    public KonstruktorException(String Str){
        this.str = Str;
    }

    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "KonstruktorException{" + "str=" + str + '}';
    }
    
    
}
