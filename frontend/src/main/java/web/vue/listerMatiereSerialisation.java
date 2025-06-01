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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Matiere;

/**
 *
 * @author lletouze
 */
public class listerMatiereSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        List<Matiere> listeMatiere = (List<Matiere>) request.getAttribute("listeMatiere");
        Boolean dejaSoutien = (Boolean) request.getAttribute("dejaSoutien");
        if (dejaSoutien == null) {
            dejaSoutien = false; // valeur par défaut si pas défini
        }

        Gson gson = new Gson();

        JsonObject finalJson = new JsonObject();
        JsonElement matiereJson = gson.toJsonTree(listeMatiere);

        finalJson.add("listeMatiere", matiereJson);
        finalJson.addProperty("dejaSoutien", dejaSoutien);

        response.getWriter().write(gson.toJson(finalJson));
    }

}
