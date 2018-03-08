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
 
    @OneToMany
    private List articulos;
    
    private ClienteDTO cliente;
    
    private PedidoDTO pedido;
    
    /**
     * constructor por defecto
     */
    //public CarritoDetailDTO()
    //{ 
    //}
    
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
        return entity;
    }
    
}
