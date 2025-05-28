/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.IntervenantEtudiant;
import metier.modele.Personne;

/**
 *
 * @author aberton1
 */
public class IntervenantEtudiantDao {
    
    public void create(IntervenantEtudiant intervenantEtudiant){
        JpaUtil.obtenirContextePersistance().persist(intervenantEtudiant);
    }
    
}