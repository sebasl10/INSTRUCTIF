/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Personne;

/**
 *
 * @author lletouze
 */
public class ConnexionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Personne maPersonne = (Personne) request.getAttribute("maPersonne");
        String type = (String) request.getAttribute("type");
        
        Gson gson = new Gson();
        JsonObject json = gson.toJsonTree(maPersonne).getAsJsonObject();
        json.addProperty("type", type);
        
        /*
        Gson gson = new Gson();
        String json = null;
        if ("Eleve".equals(type))
        {
            json = "{\"type\":\"Eleve\"}";

        }
        else
        {
            json = "{\"type\":\"Intervenant\"}";

        }
        */
        System.out.println(json.toString());
        PrintWriter out = response.getWriter();
        out.write(json.toString());
        out.close();
    }
    
}
