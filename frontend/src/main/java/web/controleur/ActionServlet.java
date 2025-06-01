/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controleur;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Personne;
import metier.service.Service;
import web.modele.AjouterFeedback;
import web.modele.Connexion;
import web.modele.DemandeSoutien;
import web.modele.Inscription;
import web.modele.NoterSoutien;
import web.modele.ObtenirURL;
import web.modele.ProfilEleve;
import web.modele.ProfilIntervenant;
import web.modele.RecupererProchainSoutien;
import web.modele.listerMatiere;
import web.vue.ConnexionSerialisation;
import web.vue.InscriptionSerialisation;
import web.vue.ObtenirURLSerialisation;
import web.vue.ProfilEleveSerialisation;
import web.vue.ProfilIntervenantSerialisation;
import web.vue.RecupererProchainSoutienSerialisation;
import web.vue.listerMatiereSerialisation;

/**
 *
 * @author lletouze
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String todo = request.getParameter("todo");
            Service service = new Service();
            
            switch(todo){
                case "Connexion":
                    Connexion connexion = new Connexion(service);
                    connexion.execute(request);
                                
                    ConnexionSerialisation connexionSerialisation = new ConnexionSerialisation();
                    connexionSerialisation.appliquer(request, response);
                    break;
                
                case "listerMatiere":
                    listerMatiere liste = new listerMatiere(service);
                    liste.execute(request);

                    listerMatiereSerialisation matiereSerialisation = new listerMatiereSerialisation();
                    matiereSerialisation.appliquer(request, response);
                    break;
                    
                case "Inscription":
                    Inscription monInscription = new Inscription(service);
                    monInscription.execute(request);

                    InscriptionSerialisation inscriptionSerialisation = new InscriptionSerialisation();
                    inscriptionSerialisation.appliquer(request, response);
                    break;
                    
                case "ProfilEleve":
                    ProfilEleve monProfilEleve = new ProfilEleve(service);
                    monProfilEleve.execute(request);

                    ProfilEleveSerialisation profilEleveSerialisation = new ProfilEleveSerialisation();
                    profilEleveSerialisation.appliquer(request, response);
                    break;
                    
                case "ProfilIntervenant":
                    ProfilIntervenant monProfilIntervenant = new ProfilIntervenant(service);
                    monProfilIntervenant.execute(request);

                    ProfilIntervenantSerialisation profilIntervenantSerialisation = new ProfilIntervenantSerialisation();
                    profilIntervenantSerialisation.appliquer(request, response);
                    break;
                    
                case "DemanderSoutien":
                    DemandeSoutien maDemande = new DemandeSoutien(service);
                    maDemande.execute(request);


                    //ProfilEleveSerialisation profilEleveSerialisation = new ProfilEleveSerialisation();
                    //profilEleveSerialisation.appliquer(request, response);
                    break;
                
                case "NoterSoutien":
                    NoterSoutien noterSoutien = new NoterSoutien(service);
                    noterSoutien.execute(request);
                break;
                
                case "AjouterFeedback":
                    AjouterFeedback ajouterFeedback = new AjouterFeedback(service);
                    ajouterFeedback.execute(request);
                break;
                
                case "RecupererProchainSoutien":
                    RecupererProchainSoutien monSoutien = new RecupererProchainSoutien(service);
                    monSoutien.execute(request);


                    RecupererProchainSoutienSerialisation recupererProchainSoutienSerialisation = new RecupererProchainSoutienSerialisation();
                    recupererProchainSoutienSerialisation.appliquer(request, response);
                break;
                case "ObtenirURL":
                    ObtenirURL monURL = new ObtenirURL(service);
                    monURL.execute(request);


                    ObtenirURLSerialisation monURLSerialisation = new ObtenirURLSerialisation();
                    monURLSerialisation.appliquer(request, response);
                break;
                    
                default:
                    // Gérer le cas où `todo` ne correspond à aucune action connue
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue : " + todo);
                    break;
            }
        }
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
