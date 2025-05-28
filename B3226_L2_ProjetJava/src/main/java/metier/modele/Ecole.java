/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aberton1
 */
@Entity
public class Ecole {
    @Id
    private String UAI;
    private String nom;
    private String IPS;
    private double lat;
    private double lng;

    public Ecole(String UAI, String nom, String IPS, double lat, double lng) {
        this.UAI = UAI;
        this.nom = nom;
        this.IPS = IPS;
        this.lat = lat;
        this.lng = lng;
    }

    public Ecole() {
    }

    public String getUAI() {
        return UAI;
    }

    public void setUAI(String UAI) {
        this.UAI = UAI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIPS() {
        return IPS;
    }

    public void setIPS(String IPS) {
        this.IPS = IPS;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    
}
