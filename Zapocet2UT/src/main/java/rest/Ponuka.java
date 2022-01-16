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
    private List<Jedlo> jedlo;
    
    public Ponuka(){
    this.jedlo = new ArrayList();
    }

    public Ponuka(String den) {
        this.den = den;
        this.jedlo = new ArrayList();
    }

    @XmlElement(name="den")
    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public List<Jedlo> getJedlo() {
        return jedlo;
    }

    public void setJedlo(List<Jedlo> jedlo) {
        this.jedlo = jedlo;
    }
    
    public void addJedlo(Jedlo jedlo){
        this.jedlo.add(jedlo);
    }  
}

