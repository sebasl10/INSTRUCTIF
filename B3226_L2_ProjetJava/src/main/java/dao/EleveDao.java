/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Personne;
import metier.modele.Soutien;

/**
 *
 * @author aberton1
 */
public class EleveDao {

    public void create(Eleve eleve) {
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }

    public void update(Eleve eleve) {
        JpaUtil.obtenirContextePersistance().merge(eleve);
    }

    public Eleve findByEmail(String email) {
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT e FROM Eleve e WHERE e.email = :email", Eleve.class);
        query.setParameter("email", email);

        List<Eleve> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Soutien> findSoutiens (Eleve eleve){
        Query query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT s FROM Soutien s "
                        + "WHERE s.eleve = :elve"
                        + " AND s.dateFin IS NOT NULL");
        query.setParameter("elve", eleve);

        List<Soutien> result = query.getResultList();
        return result;
    }
}
