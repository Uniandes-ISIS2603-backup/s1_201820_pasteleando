/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
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
    private static final Logger LOGGER = Logger.getLogger(PedidoLogic.class.getName());
    
    @Inject
    private PedidoPersistence persistence;
    
    /**
     * Retorna la lista de pedidos
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
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor con id = {0}", id);
        return persistence.find(id);
    }
    
}
