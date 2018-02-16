/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
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
public class DecoracionPersistence
{
	private static final Logger LOGGER = Logger.getLogger( DecoracionPersistence.class.getName( ) );

	@PersistenceContext( unitName = "PasteleandoPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto Decoracion que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public DecoracionEntity create(DecoracionEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Decoracion" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Decoracion" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de Decoracion con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de Decoracion que se está buscando
	 * @return null si no existe ninguna entidad Decoracion con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public DecoracionEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Decoracion por nombre ", name );

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<DecoracionEntity> query = em.createQuery( "Select e From DecoracionEntity e where e.name = :name", DecoracionEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<DecoracionEntity> sameName = query.getResultList( );
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<DecoracionEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Pasteleando" );
		TypedQuery<DecoracionEntity> query = em.createQuery( "select u from PasteleandoEntity u", DecoracionEntity.class );
		return query.getResultList( );
	}

	public DecoracionEntity find( Long id )
	{
		return em.find( DecoracionEntity.class, id );
	}

	public DecoracionEntity update( DecoracionEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( DecoracionEntity entity )
	{
		em.remove( entity );
	}
}
