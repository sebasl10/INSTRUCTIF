/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.IntervenantAutre;
import metier.modele.Personne;

/**
 *
 * @author aberton1
 */
public class IntervenantAutreDao {
    
    public void create(IntervenantAutre intervenantAutre){
        JpaUtil.obtenirContextePersistance().persist(intervenantAutre);
    }
    
}