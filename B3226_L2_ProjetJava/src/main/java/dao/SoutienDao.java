/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Soutien;

/**
 *
 * @author aberton1
 */
public class SoutienDao {
    
    public void create(Soutien soutien){
        JpaUtil.obtenirContextePersistance().persist(soutien);
    }
    
    public void update(Soutien soutien){
        JpaUtil.obtenirContextePersistance().merge(soutien);
    }
    
    public Soutien findSoutienById(Long id_soutien){
        return JpaUtil.obtenirContextePersistance().find(Soutien.class, id_soutien);
    }
}
