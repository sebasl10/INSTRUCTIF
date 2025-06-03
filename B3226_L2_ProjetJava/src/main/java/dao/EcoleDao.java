/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Ecole;
import metier.modele.Personne;

/**
 *
 * @author aberton1
 */
public class EcoleDao {
    public void create(Ecole ecole){
        JpaUtil.obtenirContextePersistance().persist(ecole);
    }
    
    public Ecole findByUAI (String ecoleUAI){
        return JpaUtil.obtenirContextePersistance().find(Ecole.class, ecoleUAI);
    }
    
    public List<Ecole> recupererEcoles() {
        TypedQuery<Ecole> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT e FROM Ecole e", Ecole.class);

        List<Ecole> result = query.getResultList();
        return result.isEmpty() ? null : result;
    }
}
