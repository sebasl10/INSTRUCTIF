/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author aberton1
 */


@Entity
public abstract class Intervenant extends Personne{

    private String numTel;
    private Integer niveauMin;
    private Integer niveauMax;
    private String username;

    public Intervenant(String numTel, Integer niveauMin, Integer niveauMax, String username, String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        this.numTel = numTel;
        if(niveauMin > niveauMax){
            niveauMin += niveauMax;
            niveauMax = niveauMin - niveauMax;
            niveauMin = niveauMin - niveauMax;
        }
        this.niveauMax = niveauMax;
        this.niveauMin = niveauMin;
        this.username = username;
    }   

    public Intervenant() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Integer getNiveauMin() {
        return niveauMin;
    }

    public void setNiveauMin(Integer niveauMin) {
        this.niveauMin = niveauMin;
    }

    public Integer getNiveauMax() {
        return niveauMax;
    }

    public void setNiveauMax(Integer niveauMax) {
        this.niveauMax = niveauMax;
    }

    @Override
    public String toString() {
        return super.toString()+"Intervenant{" + "numTel=" + numTel + ", niveauMin=" + niveauMin + ", niveauMax=" + niveauMax + ", username=" + username + '}';
    }

    
    
    
}
