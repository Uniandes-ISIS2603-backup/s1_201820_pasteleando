/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.util.List;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *Clase que representa el Carrito en la base de datos y permite su serializacion
 * @author MIGUELHOYOS
 */
@Entity
public class CarritoEntity extends BaseEntity implements Serializable
{
  private Integer cantidad;
  private double precio;
  
  @PodamExclude
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<PastelEntity> pasteles;
  
  @PodamExclude
  @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "carrito")
  private ClienteEntity cliente;
  
  @PodamExclude
  @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "carrito")
  private PedidoEntity pedido;
  
  /**
   * retorna la cantidad de productos en el carrito
   * @return cantidad
   */
  public Integer getCantidad()
  {
      return this.cantidad;
  }
  
  /**
  * actualiza la cantidad de prductos en el carrito
   * @param pCantidad 
   */
  public void setCantidad(Integer pCantidad)
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

  public List getPasteles()
  {
      return this.pasteles;
  }
  
/**
 * actualiza la lista de articulos en el carrito
 * @param lista 
 */
  public void setPasteles(List lista)
  {
    this.pasteles= lista;
  }
    
  /**
   * retorna el cliente
   * @return cliente
   */
  public ClienteEntity getCliente()
  {
    return this.cliente;
  }
    
  /**
   * actualiza el clliente
   * @param entity 
   */
   public void setCliente(ClienteEntity entity)
   {
     this.cliente = entity;
   }
    
   /**
    * retorna el pedido
    * @return pedido
    */
    public PedidoEntity getPedido()
    {
        return this.pedido;
    }
    
    /**
     * actualiza el pedido
     * @param pedido 
     */
    public void setPedido(PedidoEntity pedido)
    {
        this.pedido = pedido;
    }

}
