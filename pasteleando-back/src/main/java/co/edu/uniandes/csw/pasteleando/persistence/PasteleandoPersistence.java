/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PasteleandoEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author ISIS2603
 */
@Stateless
public class PasteleandoPersistence
{
	private static final Logger LOGGER = Logger.getLogger( PasteleandoPersistence.class.getName( ) );

	@PersistenceContext( unitName = "PasteleandoPU" )
	protected EntityManager em;

	/**
	 * @param entity objeto Pasteleando que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
	public PasteleandoEntity create( PasteleandoEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Pasteleando" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Pasteleando" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de Pasteleando con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de Pasteleando que se está buscando
	 * @return null si no existe ninguna entidad Pasteleando con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
	public PasteleandoEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Pasteleando por nombre ", name );

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<PasteleandoEntity> query = em.createQuery( "Select e From PasteleandoEntity e where e.name = :name", PasteleandoEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<PasteleandoEntity> sameName = query.getResultList( );
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<PasteleandoEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Pasteleando" );
		TypedQuery<PasteleandoEntity> query = em.createQuery( "select u from PasteleandoEntity u", PasteleandoEntity.class );
		return query.getResultList( );
	}

	public PasteleandoEntity find( Long id )
	{
		return em.find( PasteleandoEntity.class, id );
	}

	public PasteleandoEntity update( PasteleandoEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( PasteleandoEntity entity )
	{
		em.remove( entity );
	}
}
