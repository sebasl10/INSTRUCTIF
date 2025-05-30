/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author aberton1
 */
@Entity
public class IntervenantEtudiant extends Intervenant{
    private String universite;
    private String specialite;

    public IntervenantEtudiant(String specialite, String universite, String numTel, Integer niveauMin, Integer niveauMax, String username, String nom, String prenom, String email, String password) {
        super(numTel, niveauMin, niveauMax, username, nom, prenom, email, password);
        this.specialite = specialite;
        this.universite = universite;
    }

    public IntervenantEtudiant() {
    }

    public String getSpecialite() {
        return specialite;
    }

    @Override
    public String toString() {
        return "IntervenantEtudiant{" + "universite=" + universite + ", specialite=" + specialite + '}';
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    
}
