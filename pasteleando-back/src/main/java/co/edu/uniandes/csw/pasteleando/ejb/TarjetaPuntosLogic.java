/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.persistence.TarjetaPuntosPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author m.leona
 */
@Stateless
public class TarjetaPuntosLogic {
    
    @Inject
    private ClienteLogic cliente;
    
    @Inject
    private TarjetaPuntosPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de TarjetaPuntos.
     *
     * @return Colección de objetos de TarjetaPuntosEntity.
     */
    public List<TarjetaPuntosEntity> getTarjetaPuntoss() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de TarjetaPuntos a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TarjetaPuntosEntity con los datos del TarjetaPuntos consultado.
     */
    public TarjetaPuntosEntity getTarjetaPuntos(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor con id = {0}", id);
        return persistence.find(id);
    }
    
    /**
     * Se encarga de crear un TarjetaPuntos en la base de datos.
     *
     * @param entity Objeto de TarjetaPuntosEntity con los datos nuevos
     * @return Objeto de TarjetaPuntosEntity con los datos nuevos y su ID.
     */
    public TarjetaPuntosEntity createTarjetaPuntos(TarjetaPuntosEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor ");
        
        return persistence.create(entity);
    }
    
     /**
     * Actualiza la información de una instancia de TarjetaPuntos.
     *
     * @param entity Instancia de TarjetaPuntosEntity con los nuevos datos.
     * @return Instancia de TarjetaPuntosEntity con los datos actualizados.
     */
    public TarjetaPuntosEntity updateTarjetaPuntos(TarjetaPuntosEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un autor ");
        return persistence.update(entity);
    }
    
      /**
     * Elimina una instancia de TarjetaPuntos de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteTarjetaPuntos(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor ");
        persistence.delete(id);
    }
}
