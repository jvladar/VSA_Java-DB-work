/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@XmlRootElement(name = "jedlo")
public class Jedlo {
    private double cena;
    private String nazov;

    public Jedlo(double cena, String nazov) {
        this.cena = cena;
        this.nazov = nazov;
    }
    
    @XmlElement(name="cena")
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    @XmlElement(name="nazov")
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    
}
