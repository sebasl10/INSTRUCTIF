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
import metier.modele.Eleve;
import metier.modele.Soutien;

/**
 *
 * @author lletouze
 */
public class ProfilEleveSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Eleve monEleve = (Eleve) request.getAttribute("monEleve");
        List<Soutien> liste = (List<Soutien>) request.getAttribute("historique");
        Gson gson = new Gson();
        JsonElement eleveJson = gson.toJsonTree(monEleve);
        JsonElement historiqueJson = gson.toJsonTree(liste);
        JsonObject finalJson = eleveJson.getAsJsonObject();
        finalJson.add("historique", historiqueJson);
        System.out.println(finalJson);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(finalJson));
        out.close();
    }
    
}
