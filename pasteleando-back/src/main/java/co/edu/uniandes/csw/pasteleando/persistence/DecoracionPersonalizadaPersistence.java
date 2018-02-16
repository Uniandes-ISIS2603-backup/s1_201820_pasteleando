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
 *
 * @author dc.cepeda
 */
@Stateless
public class DecoracionPersonalizadaPersistence
{
	private static final Logger LOGGER = Logger.getLogger( DecoracionPersonalizadaPersistence.class.getName( ) );

	@PersistenceContext( unitName = "PasteleandoPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto DecoracionPersonalizada que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public DecoracionPersonalizadaEntity create( DecoracionPersonalizadaEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de DecoracionPersonalizadao" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de DecoracionPersonalizada" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de DecoracionPersonalizada con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de DecoracionPersonalizada que se está buscando
	 * @return null si no existe ninguna entidad DecoracionPersonalizada con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public DecoracionPersonalizadaEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de DecoracionPersonalizada por nombre ", name );

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

	public List<DecoracionPersonalizadaEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de DecoracionPersonalizada" );
		TypedQuery<DecoracionPersonalizadaEntity> query = em.createQuery( "select u from PasteleandoEntity u", DecoracionPersonalizadaEntity.class );
		return query.getResultList( );
	}

	public DecoracionPersonalizadaEntity find( Long id )
	{
		return em.find( DecoracionPersonalizadaEntity.class, id );
	}

	public DecoracionPersonalizadaEntity update( DecoracionPersonalizadaEntity entity )
	{
		return em.merge( entity );
	}

	public void delete(DecoracionPersonalizadaEntity entity )
	{
		em.remove( entity );
	}
}
    
