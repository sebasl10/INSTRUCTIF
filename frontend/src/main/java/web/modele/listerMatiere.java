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

    // Récupérer l'attribut 'dejaSoutien' dans la session
    Boolean dejaSoutien = (Boolean) request.getSession().getAttribute("dejaSoutien");
    if (dejaSoutien == null) {
        dejaSoutien = false; // par défaut false si absent
    }
    request.setAttribute("dejaSoutien", dejaSoutien);
}

}
