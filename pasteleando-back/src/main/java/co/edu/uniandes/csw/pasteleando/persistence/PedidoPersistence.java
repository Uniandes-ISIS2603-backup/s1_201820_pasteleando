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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Pedido. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ni.ramirez10
 */
@Stateless
public class PedidoPersistence {

    private static final Logger LOGGER = Logger.getLogger(PedidoPersistence.class.getName());

    @PersistenceContext(unitName = "PasteleandoPU")
    protected EntityManager em;

    /**
     * @param entity objeto Pedido que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PedidoEntity create(PedidoEntity entity) {
        LOGGER.info("Creando una nueva entidad de Pedido");
        em.persist(entity);
        LOGGER.info("Creando una entidad de Pedido");
        return entity;
    }

    /**
     * Busca si hay alguna entidad de Pedido con el nombre que se envía de
     * argumento
     *
     * @param name: Nombre de la entidad de Pedido que se está buscando
     * @return null si no existe ninguna entidad Pedido con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public PedidoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando entidades de Pedido por nombre ", name);

        // Se crea un query para buscar entidades de Pasteleando con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<PedidoEntity> query = em.createQuery("Select e From PedidoEntity e where e.name = :name", PedidoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PedidoEntity> sameName = query.getResultList();

        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    /**
     * Devuelve todos los pedidos de la base de datos.
     *
     * @return una lista con todos los pedidos que encuentre en la base de
     * datos.
     */
    public List<PedidoEntity> findAll() {
        LOGGER.info("Consultando todas las entidades de Pasteleando");
        TypedQuery<PedidoEntity> query = em.createQuery("select u from PedidoEntity u", PedidoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun pedido con el id que se envía de argumento
     *
     * @param id: id correspondiente al pedido buscado.
     * @return un pedido.
     */
    public PedidoEntity find(Long id) {
        return em.find(PedidoEntity.class, id);
    }

    /**
     * Actualiza un pedido.
     *
     * @param entity: el pedido que viene con los nuevos cambios.
     * @return un pedido con los cambios aplicados.
     */
    public PedidoEntity update(PedidoEntity entity) {
        return em.merge(entity);
    }

    /**
     *
     * Borra un pedido de la base de datos recibiendo como argumento el id
     *
     * @param id: id correspondiente al pedido a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando pedido con id={0}", id);
        PedidoEntity entity = em.find(PedidoEntity.class, id);
        em.remove(entity);
    }

}
