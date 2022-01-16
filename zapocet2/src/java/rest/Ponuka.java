/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement(name = "ponuka")
public class Ponuka {
    private String den;
    private List<Jedlo> jedla;
    
    public Ponuka(){
    this.jedla = new ArrayList();
    }

    public Ponuka(String den) {
        this.den = den;
        this.jedla = new ArrayList();
    }

    @XmlElement(name="den")
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    
    @XmlList
    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }
    
    public void addJedlo(Jedlo jedlo){
        this.jedla.add(jedlo);
    }
    
    
    
}
