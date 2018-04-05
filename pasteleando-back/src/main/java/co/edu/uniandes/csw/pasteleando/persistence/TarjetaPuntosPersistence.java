/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author m.leona
 */
@Stateless
public class TarjetaPuntosPersistence {
    
    
    @PersistenceContext(unitName = "PasteleandoPU")   
     protected EntityManager em;
    
    /**
	 * @param entity objeto TarjetaPuntos que se creará en la base de datos
	 * @return devuelve la entidad creada con un id dado por la base de datos.
	 */
     
	public TarjetaPuntosEntity create(TarjetaPuntosEntity entity )
	{
		
		em.persist( entity );
		return entity;
	}

	/**
	 * Busca si hay alguna entidad de Factura con el nombre que se envía de argumento
	 *
	 * @param name: Nombre de la entidad de Factura que se está buscando
	 * @return null si no existe ninguna entidad Factura con el nombre del argumento. Si
	 * existe alguna devuelve la primera.
	 */
        
	public TarjetaPuntosEntity findByName( String name )
	{

		// Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
		TypedQuery<TarjetaPuntosEntity> query = em.createQuery( "Select e From TarjetaPuntosEntity e where e.name = :name", TarjetaPuntosEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<TarjetaPuntosEntity> sameName = query.getResultList( );
                
		if( sameName.isEmpty( ) )
		{
			return null;
		}
		else
		{
			return sameName.get( 0 );
		}
	}

	public List<TarjetaPuntosEntity> findAll( )
	{
		TypedQuery<TarjetaPuntosEntity> query = em.createQuery( "select u from TarjetaPuntosEntity u", TarjetaPuntosEntity.class );
		return query.getResultList( );
	}

	public TarjetaPuntosEntity find( Long idCliente, Long idTarjeta )
	{
		TypedQuery<TarjetaPuntosEntity> q = em.createQuery("select p from TarjetaPuntos p where (p.cliente.id = :idCliente) and (p.id = :idTarjeta)", TarjetaPuntosEntity.class);
        q.setParameter("idCliente", idCliente);
        q.setParameter("idTarjeta", idTarjeta);
        List<TarjetaPuntosEntity> results = q.getResultList();
        TarjetaPuntosEntity tarjeta = null;
        if (results == null) {
            tarjeta = null;
        } else if (results.isEmpty()) {
            tarjeta = null;
        } else if (results.size() >= 1) {
            tarjeta = results.get(0);
        }

        return tarjeta;
	}

	public TarjetaPuntosEntity update( TarjetaPuntosEntity entity )
	{
		return em.merge( entity );
	}

	public void delete( Long id )
	{
		TarjetaPuntosEntity entity = em.find(TarjetaPuntosEntity.class, id);
                em.remove(entity);
	}
}
