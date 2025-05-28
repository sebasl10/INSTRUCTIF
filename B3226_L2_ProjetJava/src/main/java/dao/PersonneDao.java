/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Personne;

/**
 *
 * @author aberton1
 */
public class PersonneDao {

    public Personne findByEmail(String email) {
        TypedQuery<Personne> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT p FROM Personne p WHERE p.email = :email", Personne.class);
        query.setParameter("email", email);

        List<Personne> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public Personne findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Personne.class, id);
    }
    
    public Boolean checkPassword(Personne personne, String password) {
        return personne.getPassword().equals(password);
    }
}
