/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author aberton1
 */
@Entity
public class Matiere {
    @Id
    private String nom;

    public Matiere(String nom) {
        this.nom = nom;
    }

    public Matiere() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
