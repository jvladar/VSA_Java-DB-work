/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jedlo {

    public Jedlo() {
    }

    public Jedlo(String nazov, double cena) {
        this.nazov = nazov;
        this.cena = cena;
    }
    
    private String nazov;

    private double cena;

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

}
