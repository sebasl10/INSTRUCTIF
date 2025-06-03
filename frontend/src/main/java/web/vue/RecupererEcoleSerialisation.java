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
import static java.lang.System.out;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Ecole;

/**
 *
 * @author lletouze
 */
public class RecupererEcoleSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    List<Ecole> ecoles = (List<Ecole>) request.getAttribute("ecoles");
    Gson gson = new Gson();
    JsonObject finalJson = new JsonObject();
    JsonElement ecolesJson = gson.toJsonTree(ecoles);
    finalJson.add("ecoles", ecolesJson);
    System.out.println(finalJson);
    response.getWriter().write(gson.toJson(finalJson));
}


}