/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
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
public class DecoracionCatalogoPersistence
{
	private static final Logger LOGGER = Logger.getLogger( DecoracionCatalogoPersistence.class.getName( ) );

	@PersistenceContext( unitName = "PasteleandoPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto DecoracionCatalogo que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public DecoracionCatalogoEntity create(DecoracionCatalogoEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Decoracion" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Decoracion" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de DecoracionCatalogo con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de DecoracionCatalogo que se está buscando
	 * @return null si no existe ninguna entidad DecoracionCatalogo con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public DecoracionCatalogoEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Decoracion-Catalogo por nombre ", name );

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<DecoracionCatalogoEntity> query = em.createQuery( "Select e From DecoracionCatalogoEntity e where e.name = :name", DecoracionCatalogoEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<DecoracionCatalogoEntity> sameName = query.getResultList( );
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<DecoracionCatalogoEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Pasteleando" );
		TypedQuery<DecoracionCatalogoEntity> query = em.createQuery( "select u from PasteleandoEntity u", DecoracionCatalogoEntity.class );
		return query.getResultList( );
	}

	public DecoracionCatalogoEntity find( Long id )
	{
		return em.find( DecoracionCatalogoEntity.class, id );
	}

	public DecoracionCatalogoEntity update( DecoracionCatalogoEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( DecoracionCatalogoEntity entity )
	{
		em.remove( entity );
	}
}
