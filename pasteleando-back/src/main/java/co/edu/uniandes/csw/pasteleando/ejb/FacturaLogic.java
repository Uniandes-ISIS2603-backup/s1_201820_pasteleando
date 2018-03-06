/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.FacturaPersistence;
import java.util.List;
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
   // private PedidoLogic pedidoLogic;
    
    @Inject
    private TarjetaPuntosLogic tarjetaPuntos;
    
     /**
     * Obtiene la lista de los registros de Factura.
     *
     * @return Colección de objetos de FacturaEntity.
     */
    public List<FacturaEntity> getFacturas() {
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Factura a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FacturaEntity con los datos del Factura consultado.
     */
    public FacturaEntity getFactura(Long id) {
        return persistence.find(id);
    }
    
    
    public boolean validatePrecio(Integer precio)
    {
        if(precio <= 0)
        {
            return false;
        }
        return true;
    }
    /**
     * Se encarga de crear un Factura en la base de datos.
     *
     * @param entity Objeto de FacturaEntity con los datos nuevos
     * @return Objeto de FacturaEntity con los datos nuevos y su ID.
     */
    public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException{
       if(!validatePrecio(entity.getPrecio()))
       {
           throw new BusinessLogicException("El Precio no puede ser menor que 0");
       }
        
        return persistence.create(entity);
    }
    
     /**
     * Actualiza la información de una instancia de Factura.
     *
     * @param entity Instancia de FacturaEntity con los nuevos datos.
     * @return Instancia de FacturaEntity con los datos actualizados.
     */
    public FacturaEntity updateFactura(Long id,FacturaEntity entity) throws BusinessLogicException {
        if(!validatePrecio(entity.getPrecio()))
       {
           throw new BusinessLogicException("El Precio no puede ser menor que 0");
       }
        return persistence.update(entity);
    }
    
      /**
     * Elimina una instancia de Factura de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteFactura(Long id) {
        persistence.delete(id);
    }

    
}
