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
public class AccepterSoutien extends Action{

    public AccepterSoutien(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
    Boolean Accept = null;
    Intervenant monIntervenant = null;
    Soutien monSoutien = null;
    try {
        HttpSession session = request.getSession();
            Personne maPersonne = service.rechercherPersonneParId((Long) session.getAttribute("id"));
            monIntervenant = service.rechercherIntervenant(maPersonne.getMail());
            monSoutien = service.recupererSoutienEnAttente(monIntervenant);
            Accept = service.accepterSoutien(monSoutien);
            session.setAttribute("soutienId", monSoutien.getId());
    } catch (Exception ex) {
        Logger.getLogger(listerMatiere.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("Accept", Accept);
    
}
}
