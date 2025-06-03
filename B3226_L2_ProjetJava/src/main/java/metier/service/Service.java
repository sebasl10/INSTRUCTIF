/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.EcoleDao;
import dao.EleveDao;
import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import dao.PersonneDao;
import dao.SoutienDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.modele.Ecole;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Personne;
import metier.modele.Soutien;
import util.EducNetApi;
import util.GeoNetApi;
import util.Message;

/**
 *
 * @author aberton1
 */
public class Service {
    
    public Personne authentification(String email, String password) throws Exception{
        PersonneDao personneDao = new PersonneDao();
        Personne personne = null;
        Boolean is_ok = false;
        try{
            JpaUtil.creerContextePersistance();
            personne = personneDao.findByEmail(email);
            is_ok = personneDao.checkPassword(personne, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        if(!is_ok){
            throw new Exception("Invalid password");
        }
            
        return personne;
    }
    
    public Boolean inscrireEleve(Eleve eleve, String ecoleUAI) {
        Boolean is_ok = true;
        EleveDao eleveDao = new EleveDao();
        EcoleDao ecoleDao = new EcoleDao();
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();
            Ecole ecole = ecoleDao.findByUAI(ecoleUAI);
            if (ecole == null) {
                ecole = trouverEtablissement(ecoleUAI);
                //ecole = new Ecole("sgd", "dgft", "drgh", "drgh", "drgh");
                ecoleDao.create(ecole);
            }

            eleve.setEcole(ecole);
            eleveDao.create(eleve);
            
            String corps = "Bonjour " + eleve.getPrenom() + ", nous te confirmons ton inscription sur le réseau INSTRUCT'IF. Si tu as besoin d'un soutien pour tes lecons ou tes devoirs, rends-toi sur notre site pour une mise en relation avec un intervenant.";
            Message.envoyerMail("contact@instruct.if", eleve.getMail(), "Bienvenue sur le réseau INSTRUCT'IF", corps);
            
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

            String corps = "Bonjour " + eleve.getPrenom() + ", ton inscription sur le réseau INSTRUCT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.";
            Message.envoyerMail("contact@instruct.if", eleve.getMail(), "Echec de l'inscription sur le réseau INSTRUCT'IF", corps);

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }

    public Boolean recupererMdp(String email){
        Boolean is_ok = true;

        PersonneDao personneDao = new PersonneDao();
        try {
            JpaUtil.creerContextePersistance();

            Personne personne = personneDao.findByEmail(email);

            Message.envoyerMail("contact@instruct.if", email, "Mot de passe oublié", "Salut, nous avons reçu une demande de mot de passe oublié, voilà ton mot de passe: " + personne.getPassword());


        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }
    
    public Soutien demanderSoutien(Matiere matiere, String details, Eleve eleve){
        
        SoutienDao soutienDao = new SoutienDao();
        IntervenantDao intervenantDao = new IntervenantDao();
        Soutien soutien = new Soutien();
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            Intervenant intervenant = intervenantDao.findByClasse(eleve.getClasse());
            Date debut = new Date();
            soutien = new Soutien(debut, details, eleve, intervenant, matiere);
            
            soutienDao.create(soutien);
            
            SimpleDateFormat sdf = new SimpleDateFormat("HH'hh'mm");
            String message = "Bonjour " + intervenant.getPrenom() + 
                    ". Merci de prendre en charge la demande de soutien en '" + matiere.getNom() + 
                    "' demandée à " + sdf.format(debut) + " par " + eleve.getPrenom() + " en classe de " + eleve.getClasse() + "ème";
            Message.envoyerNotification(intervenant.getNumTel(), message);
            
            JpaUtil.validerTransaction();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutien;
    }

    public Boolean accepterSoutien(Soutien soutien){
        
        Boolean is_ok = true;

        SoutienDao soutienDao = new SoutienDao();
        try {
            JpaUtil.creerContextePersistance();

            soutien.setDateDebut(new Date());

            JpaUtil.ouvrirTransaction();
            soutienDao.update(soutien);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }
    
    public Boolean terminerSoutien(Soutien soutien){
        
        Boolean is_ok = true;

        SoutienDao soutienDao = new SoutienDao();
        try {
            JpaUtil.creerContextePersistance();

            soutien.setDateFin(new Date());

            JpaUtil.ouvrirTransaction();
            soutienDao.update(soutien);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }
    
    public Boolean noterSoutien(Soutien soutien, Byte note) {
        Boolean is_ok = true;

        SoutienDao soutienDao = new SoutienDao();
        try {
            JpaUtil.creerContextePersistance();

            soutien.setNote(note);

            JpaUtil.ouvrirTransaction();
            soutienDao.update(soutien);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }
    
    public Boolean ajouterFeedback(Soutien soutien, String feedback){
        Boolean is_ok = true;

        SoutienDao soutienDao = new SoutienDao();
        try {
            JpaUtil.creerContextePersistance();

            soutien.setFeedback(feedback);

            JpaUtil.ouvrirTransaction();
            soutienDao.update(soutien);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            is_ok = false;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return is_ok;
    }

    public Personne rechercherPersonneParId(Long personne_id){
        PersonneDao personneDao = new PersonneDao();
        Personne personne = null;
        
        try{
            JpaUtil.creerContextePersistance();
            personne = personneDao.findById(personne_id);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            personne = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if (personne == null){
            System.out.println("Aucune personne trouvé");
        }
            
        return personne;
    }
    
    public Eleve rechercherEleve(String email){
        EleveDao eleveDao = new EleveDao();
        Eleve eleve = null;
        
        try{
            JpaUtil.creerContextePersistance();
            eleve = eleveDao.findByEmail(email);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            eleve = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if (eleve == null){
            System.out.println("Aucun élève trouvé");
        }
            
        return eleve;
    }
    
    public Intervenant rechercherIntervenant(String email){
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant intervenant = null;
        
        try{
            JpaUtil.creerContextePersistance();
            intervenant = intervenantDao.findByEmail(email);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            intervenant = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if (intervenant == null){
            System.out.println("Aucun intervenant trouvé");
        }
        return intervenant;
    }
    
    public Intervenant rechercherIntervenantByUsername(String username){
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant intervenant = null;
        
        try{
            JpaUtil.creerContextePersistance();
            intervenant = intervenantDao.findByUsername(username);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            intervenant = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if (intervenant == null){
            System.out.println("Aucun intervenant trouvé");
        }
        return intervenant;
    }
    
    private Ecole trouverEtablissement(String ecoleUAI) throws Exception {
        Ecole ecole;
        EducNetApi api = new EducNetApi();

        List<String> result = api.getInformationEtablissement(ecoleUAI);
        if (result == null) {
            throw new Exception();
        }
        String uai = result.get(0);
        String nom = result.get(1);
        String secteur = result.get(2);
        String codeCommune = result.get(3);
        String nomCommune = result.get(4);
        String codeDepartement = result.get(5);
        String nomDepartement = result.get(6);
        String academie = result.get(7);
        String ips = result.get(8);
        System.out.println("Etablissement " + uai + ": " + nom + " à " + nomCommune + ", " + nomDepartement + ", IPS:" + ips);

        
        String adresseEtablissement = nom + ", " + nomCommune;
        LatLng coordsEtablissement = GeoNetApi.getLatLng(adresseEtablissement);
        System.out.println("coordonnees :"+coordsEtablissement);
        if(coordsEtablissement == null){
            System.out.println("Erreur, GeoNetApi n'as pas trouve de coordonnees.");
            ecole = new Ecole(uai, nom, ips, 0, 0);
        } else {
            System.out.println("Lat/Lng de cet Établissement: " + "( " + coordsEtablissement.lat + "; " + coordsEtablissement.lng + " )");
            ecole = new Ecole(uai, nom, ips, coordsEtablissement.lat, coordsEtablissement.lng);
        }

        return ecole;
    }

    public Soutien recupererSoutienEnAttente(Intervenant intervenant){
        IntervenantDao intervenantDao = new IntervenantDao();
        Soutien soutien = null;
        
        try{
            JpaUtil.creerContextePersistance();
            soutien = intervenantDao.soutienAttente(intervenant);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if(soutien == null){
            System.out.println("Aucun soutien en attente");
        }
        return soutien;
    }
    
    public List<Soutien> recupererHistoriqueIntervenant(Intervenant intervenant){
        IntervenantDao intervenantDao = new IntervenantDao();
        List<Soutien> soutiens = null;
        
        try{
            JpaUtil.creerContextePersistance();
            soutiens = intervenantDao.findSoutiens(intervenant);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            soutiens = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutiens;
    }
    
    public List<Soutien> recupererHistoriqueEleve(Eleve eleve){
        EleveDao eleveDao = new EleveDao();
        List<Soutien> soutiens = null;
        
        try{
            JpaUtil.creerContextePersistance();
            soutiens = eleveDao.findSoutiens(eleve);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            soutiens = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutiens;
    }
    
    public Soutien trouverSoutien(Long id_soutien){
        SoutienDao soutienDao = new SoutienDao();
        Soutien soutien = null;
        
        try{
            JpaUtil.creerContextePersistance();
            soutien = soutienDao.findSoutienById(id_soutien);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            soutien = null;
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutien;
    }

    public String recupererSoutienUrl(Soutien soutien){
        return soutien.getUrl();
    }

    public List<Matiere> recupererMatieres(){
        MatiereDao matiereDao = new MatiereDao();
        List<Matiere> matieres = new ArrayList<>();
        
        try{
            JpaUtil.creerContextePersistance();
            matieres = matiereDao.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return matieres;
    }
    
    public Matiere rechercherMatierebyNom(String nom){
        MatiereDao matiereDao = new MatiereDao();
        Matiere matiere = null;
        
        try{
            JpaUtil.creerContextePersistance();
            matiere = matiereDao.findByNom(nom);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if (matiere == null){
            System.out.println("Aucune matière trouvé");
        }
            
        return matiere;
    }
    
    public List<Ecole> recupererEcoles(){
        EcoleDao ecoleDao = new EcoleDao();
        List<Ecole> ecoles = null;
        
        try{
            JpaUtil.creerContextePersistance();
            ecoles = ecoleDao.recupererEcoles();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");

        } finally {
            JpaUtil.fermerContextePersistance();
        }
            
        return ecoles;
    }
}



