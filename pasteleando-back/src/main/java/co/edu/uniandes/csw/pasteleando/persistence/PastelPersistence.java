/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author MIGUELHOYOS
 */
@Stateless
public class PastelPersistence {
    @PersistenceContext( unitName = "PasteleandoPU" )
    private EntityManager em;
    
    public PastelEntity create(PastelEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public PastelEntity find(Long id)
    {
        return em.find(PastelEntity.class, id);
    }
    
    public PastelEntity findByName(String name)
    {
        TypedQuery<PastelEntity> q = em.createQuery("select u from PastelEntity where u.name = :name", PastelEntity.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }
    
    public List findAll()
    {
        Query q = em.createQuery("select u from PastelEntity u");
        return q.getResultList();
    }
    
    public PastelEntity update(PastelEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        PastelEntity entity = em.find(PastelEntity.class, id);
        em.remove(entity);
    }
}
