/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;


/**
 *
 * @author MIGUELHOYOS
 */
public class PastelDTO 
{
  private int peso;
  private double precio;
  
  	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param pasteldoEntity: Es la entidad que se va a convertir a DTO
	 */
  public PastelDTO(PastelEntity pastelEntity)
  {
      this.peso = pastelEntity.getPeso();
      this.precio = pastelEntity.getPrecio();
  }
  
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
   * @return precio
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
  	/**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
  public PastelEntity toEntity()
  {
      PastelEntity rta = new PastelEntity();
      rta.setPeso(this.peso);
      rta.setPrecio(this.precio);
      return rta;
  }
}
