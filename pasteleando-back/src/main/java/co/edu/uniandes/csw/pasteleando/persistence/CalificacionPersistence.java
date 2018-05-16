/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Clase que maneja la persistencia para calificacion.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author mp.bayonal
 */
@Stateless
public class CalificacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "PasteleandoPU")
    protected EntityManager em;
    
    public CalificacionPersistence()
    {
        LOGGER.log(Level.INFO, "CalificacionPersistance constructor");
    }

    /*
     * @param entity objeto Calificacion que se creara en la base de datos
     * @return devuelve la entidad creada con un id de la base de datos
     */
    public CalificacionEntity create(CalificacionEntity entity) {
        LOGGER.info("Creando una nueva entidad de Calificacion");
        em.persist(entity);
        return entity;
    }

    /*
     *Busca si hay una entidad Calificacion entity con el nombre dado
     * @param name: el nombre de la entidad a encontrar
     *@return si no existe 
     */
    public CalificacionEntity findByName(String name) {

        TypedQuery<CalificacionEntity> query = em.createQuery( "Select e From CalificacionEntity e where e.name = :name", CalificacionEntity.class );
		// Se remplaza el placeholder ":name" con el valor del argumento
		query = query.setParameter( "name", name );
		// Se invoca el query se obtiene la lista resultado
		List<CalificacionEntity> sameName = query.getResultList( );
                
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
     * Devuelve todas las calificaciones de la base de datos.
     *
     * @return una lista con todas las calificaciones que encuentre en la base de
     * datos, "select u from CalificacionEntity u" es como un "select * from
     * CalificacionEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        TypedQuery<CalificacionEntity> query = em.createQuery("Select e From CalificacionEntity e", CalificacionEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay alguna calificacion con el id que se envía de argumento
     *
     * @param id: id correspondiente a la calificacion buscada.
     * @return una calificacion.
     */
    public CalificacionEntity find(Long id) {
        return em.find(CalificacionEntity.class, id);
    }
 
    /**
     * Actualiza una calificacion.
     *
     * @param entity: la calificacion que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una califiacacion con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity entity) {
        return em.merge(entity);
    }

    /**
     *
     * Borra una calificacion de la base de datos recibiendo como argumento el id
     * de la calificacion
     *
     * @param id: id correspondiente a la calificacion a borrar.
     */
    public void delete(Long id) 
    {
        CalificacionEntity borrar = em.find(CalificacionEntity.class, id);
        em.remove(borrar);
    }

}
