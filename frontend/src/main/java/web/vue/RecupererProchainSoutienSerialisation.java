/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
public class RecupererProchainSoutienSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Soutien soutien = (Soutien) request.getAttribute("soutien");
        List<Soutien> historique = (List<Soutien>) request.getAttribute("historiqueEleve");
        String url = (String) request.getAttribute("url");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        
        if (soutien == null) {
            String json=gson.toJson(soutien);
            System.out.println(json);
            out.write(json);
            out.close();
        } 
        else
        {
            JsonElement soutienJson = gson.toJsonTree(soutien);
            JsonElement historiqueJson = gson.toJsonTree(historique);
            JsonElement urlJson = gson.toJsonTree(url);
            JsonObject finalJson = soutienJson.getAsJsonObject();
            finalJson.add("historique", historiqueJson);
            finalJson.add("url", urlJson);
            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
            System.out.println(finalJson);

            out.write(gson.toJson(soutienJson));
            out.close();
        }
        
    }
    
}
