/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ni.ramirez10
 */
@Stateless
public class PedidoPersistence 
{
     private static final Logger LOGGER = Logger.getLogger(PedidoPersistence.class.getName());
     
     @Inject
     private PedidoPersistence pedidoPersistence; 
            
     @PersistenceContext(unitName = "PasteleandoPU")   
     protected EntityManager em;
     
         /**
	 * @param entity objeto Pedido que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
     
	public PedidoEntity create(PedidoEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Pedido" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Pedido" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de Pedido con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de Pedido que se está buscando
	 * @return null si no existe ninguna entidad Pedido con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
        
	public PedidoEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Pedido por nombre ", name );

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<PedidoEntity> query = em.createQuery( "Select e From PedidoEntity e where e.name = :name", PedidoEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<PedidoEntity> sameName = query.getResultList( );
                
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<PedidoEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Pasteleando" );
		TypedQuery<PedidoEntity> query = em.createQuery( "select u from PasteleandoEntity u", PedidoEntity.class );
		return query.getResultList( );
	}

	public PedidoEntity find( Long id )
	{
		return em.find( PedidoEntity.class, id );
	}

	public PedidoEntity update( PedidoEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( PedidoEntity entity )
	{
		em.remove( entity );
	}
    
}
