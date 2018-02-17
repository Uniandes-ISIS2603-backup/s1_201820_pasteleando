/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author MIGUELHOYOS
 */
public class CarritoPersistance 
{
  @PersistenceContext("PasteleandoPU")
  protected EntityManager em;
  
  public CarritoEntity create(CarritoEntity entity)
  {
      em.persist(entity);
      return entity;
  }
  
  public CarritoEntity find(Long id)
  {
      return em.find(CarritoEntity.class, id);
  }
  
  public CarritoEntity findByName(String name)
  {
      TypedQuery<CarritoEntity> q = em.createQuery("select u from CarritoEntity where u.name = :name", CarritoEntity.class);
      q.setParameter("name", name);
      return q.getSingleResult();
  }
  
  public List getAll()
  {
      Query q = em.createQuery("select u from CarritoEntity u");
      return q.getResultList();
  }
  
  public CarritoEntity update(CarritoEntity entity)
  {
      return em.merge(entity);
  }
  
  public void delete(Long id)
  {
      CarritoEntity entity = em.find(CarritoEntity.class, id);
      em.remove(entity);
  }
}

