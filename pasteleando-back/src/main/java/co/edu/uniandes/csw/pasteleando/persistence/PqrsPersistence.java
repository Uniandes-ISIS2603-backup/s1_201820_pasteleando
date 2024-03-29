/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ni.ramirez10
 */
@Stateless
public class PqrsPersistence 
{
     private static final Logger LOGGER = Logger.getLogger(PqrsPersistence.class.getName());
     
     private PqrsPersistence pqrsPersistence;  
            
     @PersistenceContext(unitName = "PasteleandoPU")   
     protected EntityManager em;
     
   /**
    * @param entity objeto Pqrs que se creará en la base de datos
    * @return devuelve la entidad creada con un id dado por la base de datos.
    */
     
    public PqrsEntity create(PqrsEntity entity )
    {
        LOGGER.info( "Creando una nueva entidad de Pqrs" );
        em.persist( entity );
	LOGGER.info( "Creando una entidad de Pqrs" );
	return entity;
    }

    /**
     * Busca si hay alguna entidad de Pqrs con el nombre que se envía de argumento
     *
     * @param name: Nombre de la entidad de Pqrs que se está buscando
     * @return null si no existe ninguna entidad Pqrs con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
        
    public PqrsEntity findByName( String name )
    {
	LOGGER.log( Level.INFO, "Consultando entidades de Pqrs por nombre ", name );

	// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
	TypedQuery<PqrsEntity> query = em.createQuery( "Select e From PqrsEntity e where e.name = :name", PqrsEntity.class );
	// Se remplaza el placeholder ":name" con el valor del argumento
	query = query.setParameter( "name", name );
	// Se invoca el query se obtiene la lista resultado
	List<PqrsEntity> sameName = query.getResultList( );
                
	if( sameName.isEmpty( ) )
	{
	return null;
	}
	else
	{
            return sameName.get( 0 );
	}
    }

     /**
     * Devuelve todas los pqrs de la base de datos.
     *
     * @return una lista con todos los pqrs que encuentre en la base de
     * datos.
     */
    public List<PqrsEntity> findAll( )
    {
	LOGGER.info( "Consultando todas las entidades de Pasteleando" );
	TypedQuery<PqrsEntity> query = em.createQuery( "select u from PasteleandoEntity u", PqrsEntity.class );
	return query.getResultList( );
    }

    /**
     * Busca si hay algun pqrs con el id que se envía de argumento
     *
     * @param id: id correspondiente al pqrs buscado.
     * @return un pqrs.
     */
    public PqrsEntity find( Long id )
    {
	return em.find( PqrsEntity.class, id );
    }

    /**
     * Actualiza un pqrs.
     *
     * @param entity: el pqrs que viene con los nuevos cambios.
     * @return un pqrs con los cambios aplicados.
     */
    public PqrsEntity update( PqrsEntity entity )
    {
	return em.merge( entity );
    }

    /**
     *
     * Borra un pqrs de la base de datos recibiendo como argumento el id
     *
     * @param id: id correspondiente al pqrs a borrar.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando pqrs con id ={0}", id);
        PqrsEntity entity = em.find(PqrsEntity.class, id);
        em.remove(entity);
    }
    
    
}
