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
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class ProfilEleve extends Action{

    public ProfilEleve(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Eleve monEleve = null;
        List<Soutien> monHistorique = null;
        try {
            HttpSession session = request.getSession();
            Personne maPersonne = service.rechercherPersonneParId((Long) session.getAttribute("id"));
            monEleve = service.rechercherEleve(maPersonne.getMail());
            monHistorique = service.recupererHistoriqueEleve(monEleve);
        } catch (Exception ex) {
            Logger.getLogger(listerMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("monEleve", monEleve);
        request.setAttribute("historique", monHistorique);
    }

}
