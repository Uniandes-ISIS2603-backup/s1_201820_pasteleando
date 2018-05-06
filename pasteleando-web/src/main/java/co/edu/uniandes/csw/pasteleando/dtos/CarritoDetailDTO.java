 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import java.util.List;
import javax.persistence.OneToMany;

/**
 *Clase que extiende de {@link CarritoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la entidad de Carrito vaya a la documentacion de {@link CarritoDTO}
 *
 * 
 * @author MIGUELHOYOS
 */
public class CarritoDetailDTO extends CarritoDTO
{
    private List pasteles;
    
    private ClienteDTO cliente;
    
    private PedidoDTO pedido;
    
    /**
     * constructor por defecto
     */
    public CarritoDetailDTO()
    { 
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     * @param carritoEntity la entidad de Carrito de la cual se construye el obejeto
     */
    public CarritoDetailDTO(CarritoEntity carritoEntity) {
     super(carritoEntity); 
    }
    
    /**
     * transforma un DTO a un entity
     * @return la entidad construida a partir del DTO
     */
    public CarritoEntity toEntity()
    {
        CarritoEntity entity = super.toEntity();
        
        if(this.getPedido() != null)
            {
                entity.setPedido(this.getPedido().toEntity());
            }
            if(this.getCliente() != null)
            {
                entity.setCliente(this.getCliente().toEntity());
            }
        return entity;
    }
    
     /**
      * Obtiene el pedido
      * @return El pedido
      */
      public PedidoDTO getPedido() {
          return pedido;
      }
           
     /**
      * Define un pedido
      * @return El pedido
      */ 
      public void setPedido(PedidoDTO pedido) {
          this.pedido = pedido;
      }
      
     /**
      * Obtiene el cliente
      * @return El cliente
      */
      public ClienteDTO getCliente() {
          return cliente;
      }
    
     /**
      * Define un cliente
      * @return El cliente
      */
      public void setCliente(ClienteDTO cliente) {
          this.cliente = cliente;
      }

     /**
      * Obtiene la lista de pasteles
      * @return Pasteles
      */
      public List getPasteles() {
          return pasteles;
      }
       
    
}
