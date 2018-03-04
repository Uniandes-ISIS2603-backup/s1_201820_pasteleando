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
 *
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
    public CalificacionEntity find(String name) {

        TypedQuery<CalificacionEntity> query = em.createQuery("Select e From CalificacionEntity Where e.name = :name", CalificacionEntity.class);
        query = query.setParameter("name", name);

        List<CalificacionEntity> resultado = query.getResultList();

        if (resultado.isEmpty()) {
            return null;
        } else {
            return resultado.get(0);
        }

    }

    public List<CalificacionEntity> findAll() {
        TypedQuery<CalificacionEntity> query = em.createQuery("Select e From CalificacionEntity e", CalificacionEntity.class);
        return query.getResultList();
    }

    public CalificacionEntity find(Long id) {
        return em.find(CalificacionEntity.class, id);
    }

    public CalificacionEntity update(CalificacionEntity entity) {
        return em.merge(entity);
    }

    public void delete(CalificacionEntity entity) {
        em.remove(entity);
    }

}
