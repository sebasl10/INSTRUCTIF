/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.Ecole;

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
}
