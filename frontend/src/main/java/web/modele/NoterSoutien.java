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
public class NoterSoutien extends Action{

    public NoterSoutien(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String strId = request.getParameter("idSeance");
        Long id = Long.parseLong(strId);
        String strNote = request.getParameter("note");
        Byte note = Byte.parseByte(strNote);
        try {
            Soutien soutien = service.trouverSoutien(id);
            service.noterSoutien(soutien, note);
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
