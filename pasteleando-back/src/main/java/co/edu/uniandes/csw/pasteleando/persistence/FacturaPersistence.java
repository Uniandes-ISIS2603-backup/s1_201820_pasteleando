/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Clase que maneja la persistencia para Factura.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author m.leona
 */
@Stateless
public class FacturaPersistence {
    
    @PersistenceContext(unitName = "PasteleandoPU")   
     protected EntityManager em;
    
    /**
    * @param entity objeto Factura que se creará en la base de datos
    * @return devuelve la entidad creada con un id dado por la base de datos.
    */
    public FacturaEntity create(FacturaEntity entity )
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
    public FacturaEntity findByName( String name )
    {

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

    /**
     * Devuelve todas las facturas de la base de datos.
     *
     * @return una lista con todas las facturas que encuentre en la base de
     * datos.
     */
    public List<FacturaEntity> findAll( )
    {
        TypedQuery<FacturaEntity> query = em.createQuery( "select u from FacturaEntity u", FacturaEntity.class );
	return query.getResultList( );
    }

    /**
     * Busca si hay alguna factura con el id que se envía de argumento
     *
     * @param id: id correspondiente a la factura buscada.
     * @return una factura.
     */
    public FacturaEntity find( Long id )
    {
	return em.find( FacturaEntity.class, id );
    }

    /**
     * Actualiza una factura.
     *
     * @param entity: la factura que viene con los nuevos cambios..
     * @return una factura con los cambios aplicados.
     */
    public FacturaEntity update( FacturaEntity entity )
    {
        return em.merge( entity );
    }

    /**
     *
     * Borra una factura de la base de datos recibiendo como argumento el id
     *
     * @param id: id correspondiente a la factura a borrar.
     */
    public void delete( Long id )
    {
        FacturaEntity entity = em.find(FacturaEntity.class, id);
	em.remove( entity );
    }

}
