/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aberton1
 */



@Entity
public class Soutien {
    public enum Etat{
        EN_COURS,
        ATTENTE,
        TERMINE
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private Etat etat;
    private String details;
    private Byte note;
    private String feedback;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Intervenant intervenant;
    @ManyToOne
    private Matiere matiere;

    public Soutien(Date dateDemande, String details, Eleve eleve, Intervenant intervenant, Matiere matiere) {
        this.dateDemande = dateDemande;
        this.dateDebut = null;
        this.dateFin = null;
        this.etat = Etat.ATTENTE;
        this.details = details;
        this.note = null;
        this.feedback = null;
        this.eleve = eleve;
        this.intervenant = intervenant;
        this.matiere = matiere;
    }
    
    

    public Soutien() {
    }
    
    public String getUrl(){
        return "https://servif.insa-lyon.fr/InteractIF/visio.html?eleve="+this.eleve.getMail()+"&intervenant="+this.intervenant.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
        this.etat = Etat.ATTENTE;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
        this.etat = Etat.EN_COURS;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
        this.etat = Etat.TERMINE;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Byte getNote() {
        return note;
    }

    public void setNote(Byte note) {
        this.note = note;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    
}
