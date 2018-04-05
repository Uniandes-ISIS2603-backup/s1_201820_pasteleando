/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.TarjetaPuntosPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 * @author m.leona
 */
@Stateless
public class TarjetaPuntosLogic {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private TarjetaPuntosPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de TarjetaPuntos.
     *
     * @return Colección de objetos de TarjetaPuntosEntity.
     */
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente) throws BusinessLogicException {
        ClienteEntity cliente = null;
        cliente = clienteLogic.getById(idCliente);
       
        if(cliente==null){
            throw new BusinessLogicException("El cliente ingresado no existe");
        }
        if (cliente.getTarjeta() == null) {
            throw new BusinessLogicException("El cliente no tiene tarjeta");
        }
       
        return cliente.getTarjeta();
    }
    
    /**
     * Obtiene los datos de una instancia de TarjetaPuntos a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de TarjetaPuntosEntity con los datos del TarjetaPuntos consultado.
     */
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente, Long idTarjeta) {
        return persistence.find(idCliente, idTarjeta);
    }
    
    public boolean validatePuntos(Integer puntos)
    {
        if(puntos < 0)
        {
            return false;
        }
        
            return true;
        
    }
    public List<TarjetaPuntosEntity> getTarjetasPuntos(){
        return persistence.findAll();
    }
    
    /**
     * Se encarga de crear un TarjetaPuntos en la base de datos.
     *
     * @param entity Objeto de TarjetaPuntosEntity con los datos nuevos
     * @return Objeto de TarjetaPuntosEntity con los datos nuevos y su ID.
     */
    public TarjetaPuntosEntity createTarjetaPuntos(Long clienteId,TarjetaPuntosEntity entity) throws BusinessLogicException{
          if(!validatePuntos(entity.getNumeroPuntos()))
        {
            throw new BusinessLogicException("Los puntos no pueden ser menores que 0");
        }
          ClienteEntity cliente = clienteLogic.getById(clienteId);
          entity.setCliente(cliente);
        
        return persistence.create(entity);
    }
    
     /**
     * Actualiza la información de una instancia de TarjetaPuntos.
     *
     * @param entity Instancia de TarjetaPuntosEntity con los nuevos datos.
     * @return Instancia de TarjetaPuntosEntity con los datos actualizados.
     */
    public TarjetaPuntosEntity updateTarjetaPuntos(Long clienteId,TarjetaPuntosEntity entity) throws BusinessLogicException{
         if(!validatePuntos(entity.getNumeroPuntos()))
        {
            throw new BusinessLogicException("Los puntos no pueden ser menores que 0");
        }
        ClienteEntity cliente = clienteLogic.getById(clienteId);
        entity.setCliente(cliente);
        
        return persistence.update(entity);
    }
    
      /**
     * Elimina una instancia de TarjetaPuntos de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteTarjetaPuntos(Long clienteId,Long id) {
        
        TarjetaPuntosEntity old = getTarjetaPuntos(clienteId, id);
        persistence.delete(old.getId());
    }
}
