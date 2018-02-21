/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author monicabayona
 */
public class ClientePersistence {
    
    @PersistenceContext(unitName = "PasteleandoPU")
    protected EntityManager em;
    
    public ClienteEntity create( ClienteEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ClienteEntity findByName(String name)
    {
        TypedQuery<ClienteEntity> query = em.createQuery("Select e From ClienteEntity Where e.name = :name", ClienteEntity.class);
        query = query.setParameter("name", name);
        
        List<ClienteEntity> resultado = query.getResultList();
        
        if(resultado.isEmpty())
        {
            return null;
        }
        else return resultado.get(0);
    
    }
    
    public List<ClienteEntity> findAll()
    {
        TypedQuery query = em.createQuery("Select u From ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }
    
    public ClienteEntity find (long id)
    {
        return em.find(ClienteEntity.class, id);
        
    }
    
    public ClienteEntity update(ClienteEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(ClienteEntity entity)
    {
        em.remove(entity);
    }
    
    
}
