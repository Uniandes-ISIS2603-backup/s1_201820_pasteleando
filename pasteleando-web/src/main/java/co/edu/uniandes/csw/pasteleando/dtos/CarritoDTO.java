/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pasteleando.dtos;
import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * *CarritoDTO: obejeto de transferencia de datos para la entidad Carrito.
 * 
 * represnetaciones de los JSON que se transfieren entre el cliente y el servidor.

 * Al serializarse como JSON esta clase implementa el siguiente modelo:<br>
 * 
 * 
 * <pre>
 * <br>
 *   {
 *      "id": number,
 *      "cantidad": number,
 *      "precio" : number,
 *      "pasteles" : list
 *   }
 * </pre>
 * 
 * Por ejemplo una entidad de Carrito se representa asi:<br>
 * 
 *   {
 *      "id": 26748957,
 *      "cantidad" : 4,
 *      "precio": 280,9,
 *      "pasteles" : [ {PastelDTO},...]
 *   }
 
 * 
 * 
 * @author MIGUELHOYOS
 */
public class CarritoDTO implements Serializable
{

  private Long id;
  private Integer cantidad;
  private Double precio;
  private transient List pasteles;
  
  /**
   * constructor vacio
   */
  public CarritoDTO()
  {
      
  }
  
  
  /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param carritoEntity: Es la entidad que se va a convertir a DTO
	 */
  public CarritoDTO(CarritoEntity carritoEntity)
  {
      if( carritoEntity != null)
      {
        this.id = carritoEntity.getId();
        this.cantidad = carritoEntity.getCantidad();
        this.precio = carritoEntity.getPrecio();
        this.pasteles = carritoEntity.getPasteles();
      }
  }
 
  /**
   * retorna el id del carrito
   * @return id Identificación del carrito
   */
  public long getId()
  {
      return this.id;
  }
  /**
   * actualiza el id con el id que llega por parametro
   * @param pId Nueva identificacion
   */

  public void setId(long pId)
  {
      this.id = pId;
  }
  /**
   * retorna la cantidad de productos en el carrito
   * @return cantidad Cantidad de prod en carrito
   */

  public int getCantidad()
  {
      return this.cantidad;
  }
  /**
  * actualiza la cantidad de prductos en el carrito
   * @param pCantidad Nuevaantidad de prod en carrito
   */

  public void setCantidad(int pCantidad)
  {
      this.cantidad = pCantidad;
  }
  /**
   * retorna el precio total del carrito
   * @return precio Precio del Carrito
   */

  public double getPrecio()
  {
      return this.precio;
  }
  /**
   * actualiza el precio total del carrito
   * @param pPrecio Nuevo Precio del carrito
   */

  public void setPrecio(double pPrecio)
  {
      this.precio = pPrecio;
  }
  /**
   * retorna la lista de articulos en el carrito
   * @return pasteles Lista de articulos de pasteles
   */

  public List getPasteles()
  {
      return this.pasteles;
  }
  /**
 * actualiza la lista de articulos en el carrito
 * @param lista Lista articulos de pasteles
 */

    public void setArticulos(List lista)
    {
        this.pasteles = lista;
    }
	/**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
    public CarritoEntity toEntity()
    {
        CarritoEntity rta = new CarritoEntity();
        rta.setId(this.id);
        rta.setPrecio(this.precio);
        rta.setCantidad(this.cantidad);
        rta.setPasteles(this.pasteles);
        return rta;
    }
    
}
