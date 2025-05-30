/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Matiere;

/**
 *
 * @author aberton1
 */
public class MatiereDao {
    
    public void create(Matiere matiere){
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    
    public List<Matiere> findAll(){
        Query query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT m FROM Matiere m");
        
        List<Matiere> result = query.getResultList();
        return result.isEmpty() ? null : result;
    }
    
    public Matiere findByNom(String nom) {
        TypedQuery<Matiere> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT m FROM Matiere m WHERE m.nom = :nom", Matiere.class);
        query.setParameter("nom", nom);

        List<Matiere> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}