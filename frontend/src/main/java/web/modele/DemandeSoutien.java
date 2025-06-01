/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Matiere;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class DemandeSoutien extends Action{

    public DemandeSoutien(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String matiere = request.getParameter("matiere");
                                System.out.println("YYYYYYYYYYYYYYYYYYYYXXXXXXYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        System.out.println(matiere);

        String details = request.getParameter("details");
        try {
            HttpSession session = request.getSession();
            Personne maPersonne = service.rechercherPersonneParId((Long) session.getAttribute("id"));
            Eleve monEleve = service.rechercherEleve(maPersonne.getMail());
            
            Matiere maMatiere = service.rechercherMatierebyNom(matiere);

            System.out.println(maMatiere);
            Soutien monSoutien = service.demanderSoutien(maMatiere,details,monEleve);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(monSoutien);
            session.setAttribute("soutienId", monSoutien.getId());
            session.setAttribute("dejaSoutien", true);
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
