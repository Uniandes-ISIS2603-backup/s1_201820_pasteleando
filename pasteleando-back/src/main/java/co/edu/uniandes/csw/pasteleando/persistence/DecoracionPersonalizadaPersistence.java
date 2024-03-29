/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Clase que maneja la persistencia para decoracion personalizada.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author dc.cepeda
 */
@Stateless
public class DecoracionPersonalizadaPersistence
{
    private static final Logger LOGGER = Logger.getLogger( DecoracionPersonalizadaPersistence.class.getName( ) );

    @PersistenceContext( unitName = "PasteleandoPU" )

    protected EntityManager em;

    /**
    * @param entity objeto Decoracion que se creará en la base de datos
    * @return devuelve la entidad creada con un id dado por la base de datos.
    */
    public DecoracionPersonalizadaEntity create(DecoracionPersonalizadaEntity entity )
    {
        LOGGER.info( "Creando una nueva entidad de Decoracion Personalizada" );
        em.persist( entity );
        LOGGER.info( "Creando una entidad de Decoracion Personalizada" );
        return entity;
    }

    /**
    * Busca si hay alguna entidad de Decoracion Personalizada con el nombre que se envía de argumento
    *
    * @param name: Nombre de la entidad de Decoracion Personalizada que se está buscando
    * @return null si no existe ninguna entidad Decoracion Personalizada con el nombre del argumento. Si
    * existe alguna devuelve la primera.
    */
    public DecoracionPersonalizadaEntity findByName( String name )
    {
        LOGGER.log( Level.INFO, "Consultando entidades de Decoracion Personalizada por nombre ", name );
    
        // Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<DecoracionPersonalizadaEntity> query = em.createQuery( "Select e From DecoracionPersonalizadaEntity e where e.name = :name", DecoracionPersonalizadaEntity.class );
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter( "name", name );
        // Se invoca el query se obtiene la lista resultado
        List<DecoracionPersonalizadaEntity> sameName = query.getResultList( );
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
     * Devuelve todas las decoraciones personalizadas de la base de datos.
     *
     * @return una lista con todas las decoraciones personalizadas que encuentre en la base de
     * datos
     */
    public List<DecoracionPersonalizadaEntity> findAll( )
    {
        LOGGER.info( "Consultando todas las entidades de Pasteleando" );
        TypedQuery<DecoracionPersonalizadaEntity> query = em.createQuery( "select u from DecoracionPersonalizadaEntity u", DecoracionPersonalizadaEntity.class );
        return query.getResultList( );
    }

    /**
     * Busca si hay alguna decoracion personalizada con el id que se envía de argumento
     *
     * @param id: id correspondiente a la editorial buscada.
     * @return una editorial.
     */
    public DecoracionPersonalizadaEntity find( Long id )
    {
        return em.find( DecoracionPersonalizadaEntity.class, id );
    }

    /**
     * Actualiza una decoracion personalizada.
     *
     * @param entity: la decoracion personalizada que viene con los nuevos cambios. 
     * @return una decoracion personalizada con los cambios aplicados.
     */
    public DecoracionPersonalizadaEntity update( DecoracionPersonalizadaEntity entity )
    {
        return em.merge( entity );
    }
    
    /**
    *
    * Borra una Decoración Personalizada de la base de datos recibiendo como argumento el id
    *
    * @param id: id correspondiente a la Decoración Personalizada a borrar.
    */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Decoración  con id={0}", id);
        DecoracionPersonalizadaEntity entity = em.find(DecoracionPersonalizadaEntity.class, id);
        em.remove(entity);
    }
}

