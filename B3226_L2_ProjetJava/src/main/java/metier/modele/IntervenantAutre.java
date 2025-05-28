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
public class IntervenantAutre extends Intervenant {
    private String activite;

    public IntervenantAutre() {
    }

    public IntervenantAutre(String activite, String numTel, Integer niveauMin, Integer niveauMax, String username, String nom, String prenom, String email, String password) {
        super(numTel, niveauMin, niveauMax, username, nom, prenom, email, password);
        this.activite = activite;
    }

    

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }
    
    
}
