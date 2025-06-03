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
import metier.modele.Ecole;
import metier.modele.Matiere;
import metier.modele.Soutien;
import metier.service.Service;

/**
 *
 * @author lletouze
 */
public class RecupererEcole extends Action{

    public RecupererEcole(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
    List<Ecole> ecoles = null;
    try {
        ecoles = service.recupererEcoles();
    } catch (Exception ex) {
        Logger.getLogger(listerMatiere.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("ecoles", ecoles); 
}
}
