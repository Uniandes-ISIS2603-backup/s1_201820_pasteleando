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
 *
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
    

    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando una nueva entidad de Cliente");
        em.persist(entity);
        return entity;
    }

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

    public List<ClienteEntity> findAll() {
        TypedQuery query = em.createQuery("Select u From ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    public ClienteEntity find(long id) {
        return em.find(ClienteEntity.class, id);

    }

    public ClienteEntity update(ClienteEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        ClienteEntity borrar = em.find(ClienteEntity.class, id);
        em.remove(borrar);
    }

}
