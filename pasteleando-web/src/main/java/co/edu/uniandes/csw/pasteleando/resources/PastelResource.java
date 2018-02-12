/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 *
 * @author MIGUELHOYOS
 */
public class PastelResource 
{
   private int peso;
  private double precio;
  
  /**
   * retorna el peso del pastel
   * @return peso
   */
  @Path ("pasteles")
  @GET
  public int getPeso()
  {
      return peso;
  }
  /**
   * actualiza el peso con el peso que llega por parametro
   * @param pPeso 
   */
  @Path("pasteles")
  @PUT
  public void setPeso(int pPeso)
  {
      
  }
  /**
   * retorna el precio del pastel
   * @return 
   */
  @Path ("pasteles")
  @GET
  public double getPrecio()
  {
      return precio;
  }
  /**
   * actualiza el precio con el precio que llega por parametro
   * @param pPrecio 
   */
  @Path("pasteles")
  @PUT
  public void setPrecio(int pPrecio)
  {
      
  } 
}
