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
import metier.modele.Intervenant;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class TerminerSoutien extends Action{

    public TerminerSoutien(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
    Boolean Terminer = null;
    Soutien monSoutien = null;
    Long soutienId=null;
    try {
        HttpSession session = request.getSession();
            soutienId = (Long) session.getAttribute("soutienId");
            monSoutien = service.trouverSoutien(soutienId);
            Terminer = service.terminerSoutien(monSoutien);
    } catch (Exception ex) {
        Logger.getLogger(listerMatiere.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("Terminer", Terminer); 
}
}
