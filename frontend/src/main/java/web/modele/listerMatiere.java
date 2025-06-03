/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.modele.Soutien.Etat;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class listerMatiere extends Action{

    public listerMatiere(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
    List<Matiere> listeMatiere = null;
    try {
        listeMatiere = service.recupererMatieres();
    } catch (Exception ex) {
        Logger.getLogger(listerMatiere.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("listeMatiere", listeMatiere);
    
    Boolean dejaSoutien = false;
    // Récupérer l'attribut 'dejaSoutien' dans la session
    Long soutienId = (Long) request.getSession().getAttribute("soutienId");
    if (soutienId!=null)
    {    
        Soutien monSoutien = (Soutien) service.trouverSoutien(soutienId);
    
        if (monSoutien.getEtat() == Etat.TERMINE || monSoutien == null) {
            dejaSoutien = false; // par défaut false si absent
        } else {
            dejaSoutien= true;
        }
    }
    request.setAttribute("dejaSoutien", dejaSoutien);
}

}
