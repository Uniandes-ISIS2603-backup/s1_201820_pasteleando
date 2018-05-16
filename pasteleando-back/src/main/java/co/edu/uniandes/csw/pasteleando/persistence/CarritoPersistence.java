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
 *Clase que maneja la persistencia para Carrito.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
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
    
    /**
     * Método para persisitir la entidad en la base de datos.
     * @param entity objeto carrito que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CarritoEntity create(CarritoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
     /**
     * Busca si hay algun carrito con el id que se envía de argumento
     *
     * @param id: id correspondiente al carrito buscado.
     * @return un carrito.
     */
    public CarritoEntity find(Long id)
    {
        return em.find(CarritoEntity.class, id);
    }
    
    
    /**
     * Devuelve todos los carritos de la base de datos.
     *
     * @return una lista con todos los carritos que encuentre en la base de
     * datos, "select u from CarritoEntity u" es como un "select * from
     * CarritoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CarritoEntity> findAll()
    {
        Query q = em.createQuery("select u from CarritoEntity u");
        return q.getResultList();
    }
    
    /**
     * Actualiza un carrito.
     *
     * @param entity: el carrito que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un carrito con los cambios aplicados.
     */
    public CarritoEntity update(CarritoEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un carrito de la base de datos recibiendo como argumento el id
     * del carrito
     *
     * @param id: id correspondiente al carrito a borrar.
     */
    public void delete(Long id)
    {
        CarritoEntity entity = em.find(CarritoEntity.class, id);
        em.remove(entity);
    }
    
     /**
     * Busca si hay algun pastel en el carrito que llega como argumento
     *
     * @param idPastel: id del pastel buscado
     * @param idCarrito: id del carrito donde se busca el carrito
     * @return null si no existe el pastel en el carrito dado
     * pastel buscado dentro del carrito dado
     */
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

