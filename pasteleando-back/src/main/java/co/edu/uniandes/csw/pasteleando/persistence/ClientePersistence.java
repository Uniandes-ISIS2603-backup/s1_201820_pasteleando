/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Clase que maneja la persistencia para Cliente.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author monicabayona
 */
@Stateless
public class ClientePersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "PasteleandoPU")
    protected EntityManager em;
    
    public ClientePersistence()
    {
        LOGGER.log(Level.INFO, "ClientePersistance constructor");
    }
    

    /**
     * Método para persisitir la entidad en la base de datos.
     * @param entity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando una nueva entidad de Cliente");
        em.persist(entity);
        return entity;
    }

    /**
     * Busca si hay algun cliente con el nombre que se envía de argumento
     *
     * @param name: Nombre del cliente que se está buscando
     * @return null si no existe ningun cliente con el nombre del argumento.
     * Si existe alguna devuelve el primero.
     */
    public ClienteEntity findByName(String name) {
  
    TypedQuery<ClienteEntity> query = em.createQuery( "Select e From ClienteEntity e where e.name = :name", ClienteEntity.class );
        // Se remplaza el placeholder ":name" con el valor del argumento
        query = query.setParameter( "name", name );
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameName = query.getResultList( );
                
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
     * Devuelve todos los clientes de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos
     */
    public List<ClienteEntity> findAll() {
        TypedQuery query = em.createQuery("Select u From ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun cliente con el id que se envía de argumento
     *
     * @param id: id correspondiente al cliente buscado.
     * @return un cliente.
     */
    public ClienteEntity find(long id) {
        return em.find(ClienteEntity.class, id);

    }

    /**
     * Actualiza un cliente.
     *
     * @param entity: el cliente que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un cliente con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity entity) {
        return em.merge(entity);
    }

    /**
     * Borra un cliente de la base de datos recibiendo como argumento el id
     * del cliente
     *
     * @param id: id correspondiente al cliente a borrar.
     */
    public void delete(Long id) {
        ClienteEntity borrar = em.find(ClienteEntity.class, id);
        em.remove(borrar);
    }

}
