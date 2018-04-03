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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class DecoracionCatalogoPersistence
{
    private static final Logger LOGGER = Logger.getLogger(DecoracionCatalogoPersistence.class.getName());
    
    @PersistenceContext(unitName = "PasteleandoPU")
    protected EntityManager em;
    
    /**
     * Busca si hay alguna Decoración catálogo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Decoración Catálogo buscada.
     * @return una Decoración Catálogo.
     */
    public DecoracionCatalogoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Decoración Catálogo con id={0}", id);
        return em.find(DecoracionCatalogoEntity.class, id);
    }
    
    /**
     * Busca si hay alguna entidad de DecoracionCatalogo con el nombre que se envía de argumento
     *
     * @param categoria: Nombre de la entidad de DecoracionCatalogo que se está buscando
     * @return null si no existe ninguna entidad DecoracionCatalogo con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public DecoracionCatalogoEntity findByCategoria( String categoria )
    {
        LOGGER.log( Level.INFO, "Consultando entidades de DecoracionCatalogo por categoria ", categoria );
        
        // Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<DecoracionCatalogoEntity> query = em.createQuery("Select e From DecoracionCatalogoEntity e where e.categoria = :categoria", DecoracionCatalogoEntity.class );
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter( "categoria", categoria );
        // Se invoca el query se obtiene la lista resultado
        List<DecoracionCatalogoEntity> sameCategoria = query.getResultList( );
        if( sameCategoria.isEmpty( ) )
        {
            return null;
        }
        else
        {
            return sameCategoria.get( 0 );
        }
    }
    
    /**
     * Devuelve todas las Decoraciones Catálogo de la base de datos.
     *
     * @return una lista con todas las Decoraciones Catálogo que encuentre en la base de
     * datos, "select u from DecoracionCatalogoEntity u" es como un "select * from
     * DecoracionCatalogoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DecoracionCatalogoEntity> findAll() {
        LOGGER.info("Consultando todas las Decoraciones Catálogo");
        Query q = em.createQuery("select u from DecoracionCatalogoEntity u");
        return q.getResultList();
    }
    
    /**
     * Método para persisitir la entidad en la base de datos.
     * @param entity objeto Decoración Catálogo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DecoracionCatalogoEntity create(DecoracionCatalogoEntity entity) {
        LOGGER.info("Creando una Decoración Catálogo nueva");
        em.persist(entity);
        LOGGER.info("Decoración Catálogo creada");
        return entity;
    }
    
    /**
     * Actualiza una Decoración Catálogo.
     *
     * @param entity: la Decoración Catálogo que viene con los nuevos cambios. Por ejemplo
     * la imagen pudo cambiar. En ese caso, se haria uso del método update.
     * @return una Decoración Catálogo con los cambios aplicados.
     */
    public DecoracionCatalogoEntity update(DecoracionCatalogoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Decoración Catálogo con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     *
     * Borra una Decoración Catálogo de la base de datos recibiendo como argumento el id
     * del libro
     *
     * @param id: id correspondiente a la Decoración Catálogo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Decoración Catálogo con id={0}", id);
        DecoracionCatalogoEntity entity = em.find(DecoracionCatalogoEntity.class, id);
        em.remove(entity);
    }
}
