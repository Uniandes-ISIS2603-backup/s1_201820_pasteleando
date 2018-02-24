/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.awt.List;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author MIGUELHOYOS
 */
@Entity
public class CarritoEntity extends BaseEntity implements Serializable
{
  private int cantidad;
  private double precio;
  private List articulos;
  
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

}
