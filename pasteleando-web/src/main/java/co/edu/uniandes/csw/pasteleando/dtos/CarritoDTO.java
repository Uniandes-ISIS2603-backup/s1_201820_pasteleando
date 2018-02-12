/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pasteleando.dtos;
import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import java.awt.List;
import javax.persistence.Id;


/**
 *
 * @author MIGUELHOYOS
 */
public class CarritoDTO 
{

  private long id;
  private int cantidad;
  private double precio;
  private List articulos;
  
  	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param carritoEntity: Es la entidad que se va a convertir a DTO
	 */
  public CarritoDTO(CarritoEntity carritoEntity)
  {
      this.id = carritoEntity.getId();
      this.cantidad = carritoEntity.getCantidad();
      this.precio = carritoEntity.getPrecio();
      this.articulos = carritoEntity.getArticulos();
  }
 
  /**
   * retorna el id del carrito
   * @return id
   */
  public long getId()
  {
      return this.id;
  }
  /**
   * actualiza el id con el id que llega por parametro
   * @param pId 
   */

  public void setId(long pId)
  {
      this.id = pId;
  }
  /**
   * retorna la cantidad de productos en el carrito
   * @return cantidad
   */

  public int getCantidad()
  {
      return this.cantidad;
  }
  /**
  * actualiza la cantidad de prductos en el carrito
   * @param pCantidad 
   */

  public void setCantidad(int pCantidad)
  {
      this.cantidad = pCantidad;
  }
  /**
   * retorna el precio total del carrito
   * @return precio
   */

  public double getPrecio()
  {
      return this.precio;
  }
  /**
   * actualiza el precio total del carrito
   * @param pPrecio 
   */

  public void setPrecio(double pPrecio)
  {
      this.precio = pPrecio;
  }
  /**
   * retorna la lista de articulos en el carrito
   * @return pasteles
   */

  public List getArticulos()
  {
      return this.articulos;
  }
  /**
 * actualiza la lista de articulos en el carrito
 * @param lista 
 */

    public void setArticulos(List lista)
    {
        this.articulos = lista;
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
        rta.setArticulos(this.articulos);
        return rta;
    }
    
}
