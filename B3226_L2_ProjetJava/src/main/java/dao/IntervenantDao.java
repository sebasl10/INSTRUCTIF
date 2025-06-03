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
import metier.modele.IntervenantAutre;
import metier.modele.IntervenantEtudiant;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.modele.Soutien.Etat;

/**
 *
 * @author aberton1
 */
public class IntervenantDao {


    public Intervenant findByUsername(String username) {
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT i FROM Intervenant i WHERE i.username = :username", Intervenant.class);
        query.setParameter("username", username);

        List<Intervenant> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public Intervenant findByEmail(String email) {
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT i FROM Intervenant i WHERE i.email = :email", Intervenant.class);
        query.setParameter("email", email);

        List<Intervenant> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    
    public Intervenant findByClasse(Integer classe) {
        Query query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT i, (select count(s) from Soutien s where s.intervenant=i) nb FROM Intervenant i "
                        + "WHERE i.niveauMin <= :classe AND i.niveauMax >= :classe "
                        + "ORDER BY nb ASC");
        query.setParameter("classe", classe);

        List<Object[]> result = query.getResultList();
        return result.isEmpty() ? null : (Intervenant)result.get(0)[0];
    }
    
    public Soutien soutienAttente(Intervenant intervenant){
        Query query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT s FROM Soutien s WHERE s.intervenant = :inter AND (s.etat = :etat OR s.etat = :etat2)", Soutien.class);
        query.setParameter("inter", intervenant);
        query.setParameter("etat", Etat.ATTENTE);
        query.setParameter("etat2", Etat.EN_COURS);

        List<Soutien> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public List<Soutien> findSoutiens (Intervenant intervenant){
        Query query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT s FROM Soutien s "
                        + "WHERE s.intervenant = :inter AND s.etat = :etat");
        query.setParameter("inter", intervenant);
        query.setParameter("etat",Etat.TERMINE);

        List<Soutien> result = query.getResultList();
        return result;
    }
}
