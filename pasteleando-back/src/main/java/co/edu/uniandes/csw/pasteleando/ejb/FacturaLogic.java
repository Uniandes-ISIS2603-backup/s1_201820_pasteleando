/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import co.edu.uniandes.csw.pasteleando.persistence.FacturaPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @Factura m.leona
 */
@Stateless
public class FacturaLogic {
    
    @Inject
    private FacturaPersistence persistence;
    
    //@Inject
   // private ClienteLogic clienteLogic;
    
    @Inject
    private TarjetaPuntosLogic tarjetaPuntos;
    
     /**
     * Obtiene la lista de los registros de Factura.
     *
     * @return Colección de objetos de FacturaEntity.
     */
    public List<FacturaEntity> getFacturas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Factura a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FacturaEntity con los datos del Factura consultado.
     */
    public FacturaEntity getFactura(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor con id = {0}", id);
        return persistence.find(id);
    }
    
    /**
     * Se encarga de crear un Factura en la base de datos.
     *
     * @param entity Objeto de FacturaEntity con los datos nuevos
     * @return Objeto de FacturaEntity con los datos nuevos y su ID.
     */
    public FacturaEntity createFactura(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor ");
        
        return persistence.create(entity);
    }
    
     /**
     * Actualiza la información de una instancia de Factura.
     *
     * @param entity Instancia de FacturaEntity con los nuevos datos.
     * @return Instancia de FacturaEntity con los datos actualizados.
     */
    public FacturaEntity updateFactura(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un autor ");
        return persistence.update(entity);
    }
    
      /**
     * Elimina una instancia de Factura de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteFactura(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor ");
        persistence.delete(id);
    }
}
