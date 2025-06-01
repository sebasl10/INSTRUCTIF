/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
        Gson gson = new Gson();
        String json=gson.toJson(soutien);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
    
}
