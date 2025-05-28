/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class Inscription extends Action{

    public Inscription(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom"); 
        String prenom = request.getParameter("prenom");
        String dateStr = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codeEtablissement = request.getParameter("codeEtablissement");
        Integer classe = Integer.parseInt(request.getParameter("classe"));
        System.out.println(email);
        System.out.println(password);
        Eleve monEleve = null;
        try {
            Eleve testEleve = new Eleve(prenom, nom, email, password, date, classe);
            if (service.inscrireEleve(testEleve, codeEtablissement))
                    {
                        monEleve = testEleve;
                        HttpSession session = request.getSession();
                        session.setAttribute("id",monEleve.getId());
                        session.setAttribute("type","Eleve");
                    }
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("monEleve", monEleve);

    }

}
