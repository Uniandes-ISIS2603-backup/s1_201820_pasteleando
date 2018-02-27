/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author m.leona
 */
@Stateless
public class FacturaPersistence {
     private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    
    @PersistenceContext(unitName = "PasteleandoPU")   
     protected EntityManager em;
    
    /**
	 * @param entity objeto Factura que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
     
	public FacturaEntity create(FacturaEntity entity )
	{
		LOGGER.info( "Creando una nueva entidad de Factura" );
		em.persist( entity );
		LOGGER.info( "Creando una entidad de Factura" );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de Factura con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de Factura que se está buscando
	 * @return null si no existe ninguna entidad Factura con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
        
	public FacturaEntity findByName( String name )
	{
		LOGGER.log( Level.INFO, "Consultando entidades de Factura por nombre ", name );

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<FacturaEntity> query = em.createQuery( "Select e From FacturaEntity e where e.name = :name", FacturaEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<FacturaEntity> sameName = query.getResultList( );
                
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<FacturaEntity> findAll( )
	{
		LOGGER.info( "Consultando todas las entidades de Pasteleando" );
		TypedQuery<FacturaEntity> query = em.createQuery( "select u from PasteleandoEntity u", FacturaEntity.class );
		return query.getResultList( );
	}

	public FacturaEntity find( Long id )
	{
		return em.find( FacturaEntity.class, id );
	}

	public FacturaEntity update( FacturaEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( FacturaEntity entity )
	{
		em.remove( entity );
	}

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
