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
public class IntervenantEnseignant extends Intervenant {
    private String type_etab;
    
    public IntervenantEnseignant() {
    }

    public IntervenantEnseignant(String type_etab, String numTel, Integer niveauMin, Integer niveauMax, String username, String nom, String prenom, String email, String password) {
        super(numTel, niveauMin,  niveauMax, username, nom, prenom, email, password);
        this.type_etab = type_etab;
    }

    

    public String getType_etab() {
        return type_etab;
    }

    public void setType_etab(String type_etab) {
        this.type_etab = type_etab;
    }
    
}
