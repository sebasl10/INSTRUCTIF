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
public class Connexion extends Action{

    public Connexion(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        Personne maPersonne = null;
        try {
            System.out.println("Authentification");
            maPersonne = service.authentification(email, password);
            System.out.println(maPersonne);
            System.out.println("maPersonne");
            request.setAttribute("maPersonne", maPersonne);
            HttpSession session = request.getSession();
            session.setAttribute("id",maPersonne.getId());
            if (maPersonne instanceof Eleve)
            {
                session.setAttribute("type","Eleve");
                request.setAttribute("type","Eleve");
            } 
            else if (maPersonne == null){
                session.setAttribute("type","Null");
                request.setAttribute("type","Null");
            }
            else {
                session.setAttribute("type","Intervenant");
                request.setAttribute("type","Intervenant");
            }
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
