/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PedidoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Pedido.
 * @author ni.ramirez10
 */

@Stateless
public class PedidoLogic 
{
    private static final Logger LOGGER = Logger.getLogger( PedidoLogic.class.getName());
    
    @Inject
    private PedidoPersistence persistence;
    
    /**
     * Devuelve la lista de pedidos
     * @return Colección de objetos de PedidoEntity
     */
    
    public List<PedidoEntity> getPedidos() 
    {
        LOGGER.info("Inicia proceso de consultar todas los pedidos");
        List<PedidoEntity> pedidos = persistence.findAll(); 
        
        return pedidos; 
    }
    
    /**
     * Busca un pedido a partir de su identificador (id)
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PedidoEntity con los datos del pedido consultado.
     */
    
    public PedidoEntity getPedido(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un pedido con id = {0}", id);
        
        return persistence.find(id);
    }
    
    /**
     * Crea un nuevo pedido.
     * @param entity La entidad de tipo pedido a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el identificador ya existe en la persitencia.
     */
    
    public PedidoEntity createPedido(PedidoEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación del pedido");
        
        if( !persistence.find(entity.getId()).equals(null) )
        {
            throw new BusinessLogicException("El pedido con el id:" + entity.getId() + " ya existe");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del pedido");
        
        return entity;
    }
    
    /**
     * Actualizar un pedido a partir del identificador (id)
     * @param id El ID del pedido a actualizar
     * @param entity La entidad de pedido con los cambios deseados
     * @return La entidad de pedido luego de actualizarla
     * @throws BusinessLogicException Si el identificador ya existe en la persitencia.
     */
    
    public PedidoEntity updatePedido(Long id, PedidoEntity entity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pedido con id ={0}", id);
        
       if( !persistence.find(entity.getId()).equals(null) )
        {
            throw new BusinessLogicException("El pedido con el id:" + entity.getId() + " ya existe");
        }
       
        PedidoEntity pEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pedido con id ={0}", entity.getId());
        
        return pEntity;
    }
    
    /**
     * Eliminar un pedido a partir del identificador (id)
     * @param id El ID del pedido a eliminar
     */
    
    public void deletePedido(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el pedido con id ={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el pedido con id ={0}", id);
    }
    
    /**
     * Obtiene una lista de Pqrs asociadas a un pedido
     * @param pedidoId Identificador del pedido
     * @return Lista de Pqrs asociadas a un pedido
     */
    
    public List<PqrsEntity> listPqrs(Long pedidoId) 
    {
        return getPedido(pedidoId).getPqrs(); 
    }
    
    /**
     * Obtiene una lista de calificaciones asociadas a un pedido
     * @param pedidoId Identificador del pedido 
     * @return Lista de calificaciones asociadas a un pedido
     */
    
    public List<CalificacionEntity> listCalificaciones(Long pedidoId) 
    {
        return getPedido(pedidoId).getCalificaciones(); 
    }
}
