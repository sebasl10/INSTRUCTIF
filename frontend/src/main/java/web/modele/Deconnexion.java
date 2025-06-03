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
import metier.modele.Personne;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class Deconnexion extends Action{

    public Deconnexion(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("id","");
        session.invalidate();
    }

}
