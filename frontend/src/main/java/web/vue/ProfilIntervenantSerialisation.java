/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervenant;
import metier.modele.Soutien;

/**
 *
 * @author lletouze
 */
public class ProfilIntervenantSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Intervenant monIntervenant = (Intervenant) request.getAttribute("monIntervenant");
        List<Soutien> liste = (List<Soutien>) request.getAttribute("historique");
        Gson gson = new Gson();
        JsonElement intervenantJson = gson.toJsonTree(monIntervenant);
        JsonElement historiqueJson = gson.toJsonTree(liste);
        JsonObject finalJson = intervenantJson.getAsJsonObject();
        finalJson.add("historique", historiqueJson);
        System.out.println(finalJson);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(finalJson));
        out.close();
    }
    
}
