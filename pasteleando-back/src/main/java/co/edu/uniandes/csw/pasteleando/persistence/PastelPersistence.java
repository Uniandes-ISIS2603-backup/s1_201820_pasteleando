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

/**
 * Clase que maneja la persistencia para Pastel.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author MIGUELHOYOS
 */
@Stateless
public class PastelPersistence {
    @PersistenceContext( unitName = "PasteleandoPU" )
    private EntityManager em;
    
    public PastelPersistence()
    {
        
    }
    
    /**
     * Método para persisitir la entidad en la base de datos.
     * @param entity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PastelEntity create(PastelEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * Busca si hay algun pastel con el id que se envía de argumento
     *
     * @param id: id correspondiente al pastel buscado.
     * @return un pastel.
     */
    public PastelEntity find(Long id)
    {
        return em.find(PastelEntity.class, id);
    }
    
     /**
     * Devuelve todas los pasteles de la base de datos.
     *
     * @return una lista con todos los pasteles que encuentre en la base de
     * datos.
     */
    public List findAll()
    {
        Query q = em.createQuery("select u from PastelEntity u");
        return q.getResultList();
    }
    
    /**
     * Actualiza un pastel.
     *
     * @param entity: el pastel que viene con los nuevos cambios.
     * @return un pastel con los cambios aplicados.
     */
    public PastelEntity update(PastelEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     *
     * Borra un pastel de la base de datos recibiendo como argumento el id
     *
     * @param id: id correspondiente al pastel a borrar.
     */
    public void delete(Long id)
    {
        PastelEntity entity = em.find(PastelEntity.class, id);
        em.remove(entity);
    }
}
