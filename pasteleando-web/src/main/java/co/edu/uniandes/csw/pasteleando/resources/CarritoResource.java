/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import java.awt.List;
import javax.persistence.Id;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author MIGUELHOYOS
 */
public class CarritoResource 
{
@Id
  private long id;
  private int cantidad;
  private double precio;
  private List articulos;
  
 
  /**
   * retorna el id del carrito
   * @return id
   */
  @Path ("carritos")
  @GET
  @Produces("application/json")
  @Consumes("application/json")
  public long getId()
  {
      return this.id;
  }
  /**
   * actualiza el id con el id que llega por parametro
   * @param pId 
   */
  @Path ("carritos")
  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  public void setId(long pId)
  {
      this.id = pId;
  }
  /**
   * retorna la cantidad de productos en el carrito
   * @return cantidad
   */
  @Path ("carritos")
  @GET
  @Produces("application/json")
  @Consumes("application/json")
  public int getCantidad()
  {
      return this.cantidad;
  }
  /**
  * actualiza la cantidad de prductos en el carrito
   * @param pCantidad 
   */
  @Path ("carritos")
  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  public void setCantidad(int pCantidad)
  {
      this.cantidad = pCantidad;
  }
  /**
   * retorna el precio total del carrito
   * @return precio
   */
  @Path ("carritos")
  @GET
  @Produces("application/json")
  @Consumes("application/json")
  public double getPrecio()
  {
      return this.precio;
  }
  /**
   * actualiza el precio total del carrito
   * @param pPrecio 
   */
  @Path ("carritos")
  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  public void setPrecio(double pPrecio)
  {
      this.precio = pPrecio;
  }
  /**
   * retorna la lista de articulos en el carrito
   * @return pasteles
   */
  @Path ("carritos")
  @GET
  @Produces("application/json")
  @Consumes("application/json")
  public List getArticulos()
  {
      return this.articulos;
  }
  /**
 * actualiza la lista de articulos en el carrito
 * @param lista 
 */
  @Path ("carritos")
  @PUT
  @Produces("application/json")
  @Consumes("application/json")
    public void setArticulos(List lista)
    {
        this.articulos = lista;
    }
    /**
     * retorna el articulo que con el id que llega por parametro 
     * @param id
     * @return el articulo dentro del carrito con el id que llega por parametro
     */
    @Path("{id: \\d+}")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public PastelDTO getArticuloById(@PathParam(id) long pId)
    {
        return null;
    }
    /**
     * agrega un producto al carrito
     * @param id 
     * @post se agrega el producto al carrito y se actualiza la cantidad y el precio tambien
     */
    @Path("{id : \\d++")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public void agregarArticuloAlCarrito(@PathParam(id) long id)
    {
        
    }
    /**
     * elimina un articlo del carrito
     * @param id 
     * @post se elimina el producto del carrito y se actualiza la cantidad y el precio tambien
     */
    @Path("{id : \\d++")
    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    public void eliminarArticuloDelCarrito(@PathParam(id) long id)
    {
        
    }
    /**
     * aplica un descuento y se ve afectado en el precio
     * @param cantidad 
     */
    @Path ("carritos")
    @PUT
    public void aplicarDescuento(int cantidad)
    {
        
    }    
}
