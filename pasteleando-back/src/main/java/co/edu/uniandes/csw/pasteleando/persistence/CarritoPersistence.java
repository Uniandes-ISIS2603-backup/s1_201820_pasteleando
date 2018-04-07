/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MIGUELHOYOS
 */
@Stateless
public class CarritoPersistence
{
    @PersistenceContext( unitName = "PasteleandoPU" )
    protected EntityManager em;
    
    public CarritoPersistence()
    {
        
    }
    
    public CarritoEntity create(CarritoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public CarritoEntity find(Long id)
    {
        return em.find(CarritoEntity.class, id);
    }
    
    
    public List<CarritoEntity> findAll()
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
    
    public PastelEntity findPastelByCarrito(Long idPastel, Long idCarrito)
    {
        PastelEntity rta = null;
        CarritoEntity carrito = em.find(CarritoEntity.class, idCarrito);
        List<PastelEntity> listaPasteles = carrito.getPasteles();
         boolean enc = false;
        for(int i = 0; i<listaPasteles.size() && !enc; i++)
        {
            if(listaPasteles.get(i).getId() == idPastel)
            {
                enc = true;
                rta = listaPasteles.get(i);
            }
        }
        return rta;
     
    }
}

