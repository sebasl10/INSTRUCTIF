/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author aberton1
 */
@Entity
public class Eleve extends Personne{
    @ManyToOne
    private Ecole ecole;
    @Temporal(TemporalType.DATE)
    private Date naissance;
    private Integer classe;

    public Eleve(String nom, String prenom, String mail, String password, Date naissance, Integer classe) {
        super(nom, prenom, mail, password);
        this.ecole = null;
        this.naissance = naissance;
        this.classe = classe;
    }
    

    public Eleve() {
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }


    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    
}
