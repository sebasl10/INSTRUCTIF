package console;

import dao.EcoleDao;
import dao.EleveDao;
import dao.IntervenantEnseignantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import java.util.Date;
import java.util.List;
import metier.modele.Ecole;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.IntervenantEnseignant;
import metier.modele.Matiere;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.service.Service;

public class Main {

    public static void main(String[] args) {
        JpaUtil.desactiverLog();
        JpaUtil.creerFabriquePersistance();
        
        /*
        Service service = new Service();
        Matiere matiere = service.rechercherMatierebyNom("maths");
        System.out.println(matiere.getNom());
        */
        
        testerInscrireEleve();
        testerAuthentification();
        testerDemanderSoutien();
        testerTrouverEleve();
        testerTrouverSoutien();
        testerHistorique();
        testerMatieres();
        JpaUtil.fermerFabriquePersistance();
    }
    
    private static void testerInscrireEleve() {
        System.out.println("TEST: Inscription eleve");
        Service service = new Service();

        Eleve e2 = new Eleve("Test","TEST", "test@test.com", "abcd", new Date(1234546), 6);
        
        service.inscrireEleve(e2, "0692155T");
        
        System.out.println(e2.getNom());

        
    }
    
    private static void testerAuthentification(){
        System.out.println("TEST: Auth eleve");
        Service service = new Service();
        Personne personne = null;
        
        System.out.println( "wrong password test:" );
        try{
            personne = service.authentification("test@test.com", "abcdefg");
            System.out.println("ok");
        } catch (Exception ex){
            System.out.println( "invalid");
        }
        
        
        System.out.println( "good password test:" );
        try{
            personne = service.authentification("test@test.com", "abcd");
            System.out.println("ok");
        } catch (Exception ex){
            System.out.println( "invalid");
        }

        if(personne != null){
            Eleve eleve = null;
            if(personne instanceof Eleve)
                eleve = (Eleve)personne;
                System.out.println(eleve.getMail());
                System.out.println(eleve.getNaissance());
        }
        //System.out.println(((Eleve)personne).getNaissance());
        
    }

    private static void testerDemanderSoutien() {
        System.out.println("TEST: Demande soutien");
        Service service = new Service();
        EleveDao eleveDao = new EleveDao();
        EcoleDao ecoleDao = new EcoleDao();
        IntervenantEnseignantDao enseignantDao = new IntervenantEnseignantDao();
        MatiereDao matiereDao = new MatiereDao();
        Eleve eleve = null;
        IntervenantEnseignant enseignant = null;
        Matiere matiere = null;
        Soutien soutien1 = null;
        Soutien soutien2 = null;
        
        // Initialisation des données necessaires
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            eleve = new Eleve("Test","TEST", "test2@test.com", "abcd", new Date(1234546), 6);
            Ecole uneEcole = new Ecole("67UCBSDUCB", "INSA Lyon", "IPS", 45.7, 56.8);
            ecoleDao.create(uneEcole);
            eleveDao.create(eleve);
            eleve.setEcole(uneEcole);
            
            //service.inscrireEleve(eleve, "0692155T");
            enseignant = new IntervenantEnseignant("college", "010203040506", 3, 4, "apinto", "PINTO", "Alfred", "test@email.com", "password1234");
            enseignantDao.create(enseignant);
            enseignant = new IntervenantEnseignant("xdthe", "653368386", 1, 2, "cfhcfjh", "xdgxdrg", "Alfxjcyjfred", "vgyv@email.com", "cftctf");
            enseignantDao.create(enseignant);
            enseignant = new IntervenantEnseignant("dxthgege", "38383643", 5, 6, "guyjvgjy", "xdrxthc", "xtdhxth", "vucrterxdrt@email.com", "xrdfcf");
            enseignantDao.create(enseignant);
            matiere = new Matiere("maths");
            matiereDao.create(matiere);
            matiere = new Matiere("physique");
            matiereDao.create(matiere);
            
            JpaUtil.validerTransaction();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Erreur");
            return;

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        // Creation du soutien
        soutien1 = service.demanderSoutien(matiere, "je galère", eleve);

        System.out.println(soutien1.getUrl());
        
        // Verification du choix de l'intervenant
        soutien2 = service.demanderSoutien(matiere, "je galère2", eleve);

        System.out.println(soutien2.getUrl());
        System.out.println(soutien2.getIntervenant().getUsername());
        System.out.println(soutien2.getEtat());
        
        // Intervenant rejoint le soutien
        service.accepterSoutien(soutien1);
        System.out.println(soutien1.getEtat());
        
        service.accepterSoutien(soutien2);
        System.out.println(soutien2.getEtat());
        
        // Intervenant termine le soutien
        service.terminerSoutien(soutien1);
        System.out.println(soutien1.getEtat());
        
        service.terminerSoutien(soutien2);
        System.out.println(soutien2.getEtat());
        
        // Eleve note le soutien
        service.noterSoutien(soutien2, (byte)3);
        System.out.println(soutien2.getNote());
        
        // Intervenant ajoute un feedback
        service.ajouterFeedback(soutien2, "aaaaaaa bbbbbbb ccccc");
        System.out.println(soutien2.getFeedback());
    }
    
    private static void testerTrouverEleve(){
        System.out.println("TEST: Trouver eleve");
        
        Service service = new Service();
        Eleve eleve = service.rechercherEleve("test@test.com");
            
        
        System.out.println(eleve.getNom());
    }

    private static void testerTrouverSoutien(){
        System.out.println("TEST: Trouver soutien");
        
        Service service = new Service();
        Soutien soutien = service.trouverSoutien(new Long(6));
            
        
        System.out.println(soutien.getEleve().getNom());
    }

    private static void testerHistorique(){
        System.out.println("TEST: Historique");
        
        Service service = new Service();
        Intervenant intervenant = service.rechercherIntervenant("test@email.com");
        List<Soutien> soutiens = service.recupererHistoriqueIntervenant(intervenant);
        
        for(Soutien soutien : soutiens){
            System.out.println(soutien.getEleve().getNom());
        }

        Eleve eleve = service.rechercherEleve("test@test.com");
        List<Soutien> soutiensE = service.recupererHistoriqueEleve(eleve);
        for(Soutien soutienE : soutiensE){
            System.out.println(soutienE.getEleve().getNom());
        }
    }
    
    
    private static void testerMatieres(){
        System.out.println("TEST: Matieres");
        
        Service service = new Service();
        List<Matiere> matieres = service.recupererMatieres();
        
        for(Matiere matiere : matieres){
            System.out.println(matiere.getNom());
        }
    }

    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }

}