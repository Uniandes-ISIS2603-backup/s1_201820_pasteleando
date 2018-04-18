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
     * Buscar una promoción
     *
     * Busca si hay alguna promoción asociada a una decoración del catálogo y con un ID específico
     * @param decoracionCatalogoId El ID de la decoración del catálogo con respecto al cual se busca
     * @param promocionId El ID de la promoción buscada
     * @return La promoción encontrada o null. Nota: Si existe una o más promociones
     * devuelve siempre la primera que encuentra
     */
    public PromocionEntity find(Long decoracionCatalogoId, Long promocionId) {
        TypedQuery<PromocionEntity> q = em.createQuery("select p from PromocionEntity p where (p.decoracionCatalogo.id = :decoracionCatalogoId) and (p.id = :promocionId)", PromocionEntity.class);
        q.setParameter("decoracionCatalogoId", decoracionCatalogoId);
        q.setParameter("promocionId", promocionId);
        
        
        return em.find(PromocionEntity.class, promocionId);
    }
    
    public List<PromocionEntity> findAll( )
    {
        LOGGER.info( "Consultando todas las entidades de promoción" );
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
    
    /**
     * Eliminar una promoción.
     *
     * Elimina la promoción asociada al ID que recibe.
     * @param id El ID de la promoción que se desea borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando promocion con id={0}", id);
        PromocionEntity entity = em.find(PromocionEntity.class, id);
        em.remove(entity);
    }
}
