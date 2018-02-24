/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class PromocionPersistence
{
    private static final Logger LOGGER = Logger.getLogger( PromocionPersistence.class.getName( ) );
    
    @PersistenceContext( unitName = "PasteleandoPU" )
    protected EntityManager em;
    
    
    /**
     * Busca si hay alguna Promocion con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Promocion buscada.
     * @return una Promocion.
     */
    public PromocionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Promocion con id={0}", id);
        return em.find(PromocionEntity.class, id);
    }
    
    /**
     * Busca si hay alguna entidad de Promocion con el nombre que se envía de argumento
     *
     * @param name: Nombre de la entidad de Promocion que se está buscando
     * @return null si no existe ninguna entidad Promocion con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public PromocionEntity findByName( String name )
    {
        LOGGER.log( Level.INFO, "Consultando entidades de Promocion por nombre ", name );
        
        // Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<PromocionEntity> query = em.createQuery("Select e From PromocionEntity e where e.name = :name", PromocionEntity.class );
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter( "name", name );
        // Se invoca el query se obtiene la lista resultado
        List<PromocionEntity> sameName = query.getResultList( );
        if( sameName.isEmpty( ) )
        {
            return null;
        }
        else
        {
            return sameName.get( 0 );
        }
    }
    
    public List<PromocionEntity> findAll( )
    {
        LOGGER.info( "Consultando todas las entidades de Pasteleando" );
        TypedQuery<PromocionEntity> query = em.createQuery( "select u from PasteleandoEntity u", PromocionEntity.class );
        return query.getResultList( );
    }
    
    /**
     * @param entity objeto Promocion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PromocionEntity create(PromocionEntity entity )
    {
        LOGGER.info( "Creando una nueva entidad de Promocion" );
        em.persist( entity );
        LOGGER.info( "Creando una entidad de Promocion" );
        return entity;
    }
    
    public PromocionEntity update( PromocionEntity entity )
    {
        return em.merge( entity );
    }
    
    public void delete( PromocionEntity entity )
    {
        em.remove( entity );
    }
}
