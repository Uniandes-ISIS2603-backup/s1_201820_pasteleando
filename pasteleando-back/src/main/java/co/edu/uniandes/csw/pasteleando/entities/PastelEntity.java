/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author MIGUELHOYOS
 */
@Entity
public class PastelEntity extends BaseEntity implements Serializable
{
  private int peso;
  private double precio;
  
  @PodamExclude
  @ManyToOne
  private DecoracionEntity decoracion;
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
  
      /**
     * Devuelve la decoración asociada a este pastel
     * @return Entidad de tipo Decoracion
     */
    public DecoracionEntity getDecoracion() {
        return decoracion;
    }

    /**
     * Modifica la decoración asociada a este pastel
     * @param decoracion La nueva decoracion
     */
    public void setDecoracion(DecoracionEntity decoracion) {
        this.decoracion = decoracion;
    }

}