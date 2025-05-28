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
public class DemanderSoutien extends Action{

    public DemanderSoutien(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String matiere = request.getParameter("matiere");
        String details = request.getParameter("details");
        try {
            HttpSession session = request.getSession();
            Personne maPersonne = service.rechercherPersonneParId((Long) session.getAttribute("id"));
            Eleve monEleve = service.rechercherEleve(maPersonne.getMail());
            Matiere maMatiere = new Matiere(matiere);
            Soutien monSoutien = service.demanderSoutien(maMatiere,details,monEleve);
            session.setAttribute("soutienId", monSoutien.getId());
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
