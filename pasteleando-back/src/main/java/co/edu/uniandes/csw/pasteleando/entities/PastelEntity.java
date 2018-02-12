/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

/**
 *
 * @author MIGUELHOYOS
 */
public class PastelEntity 
{
    private int peso;
  private double precio;
  
  /**
   * retorna el peso del pastel
   * @return peso
   */

  public int getPeso()
  {
      return peso;
  }
  /**
   * actualiza el peso con el peso que llega por parametro
   * @param pPeso 
   */

  public void setPeso(int pPeso)
  {
      this.peso = pPeso;
  }
  /**
   * retorna el precio del pastel
   * @return 
   */

  public double getPrecio()
  {
      return precio;
  }
  /**
   * actualiza el precio con el precio que llega por parametro
   * @param pPrecio 
   */

  public void setPrecio(double pPrecio)
  {
      this.precio = pPrecio;
  }
}


