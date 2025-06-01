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
public class AjouterFeedback extends Action{

    public AjouterFeedback(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String strId = request.getParameter("id");
        Long id = Long.parseLong(strId);
        String feedback = request.getParameter("feedback");
        try {
            Soutien soutien = service.trouverSoutien(id);
            service.ajouterFeedback(soutien, feedback);
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
