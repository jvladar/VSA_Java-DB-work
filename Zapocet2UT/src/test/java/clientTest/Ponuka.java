/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ponuka {

    public Ponuka() {
        jedlo  = new ArrayList<>();
    }
    
    private String den;

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    private List<Jedlo> jedlo;

    public List<Jedlo> getJedlo() {
        return jedlo;
    }

    public void setJedlo(List<Jedlo> jedlo) {
        this.jedlo = jedlo;
    }
    
}
