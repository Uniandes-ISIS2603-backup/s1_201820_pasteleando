/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
/**
 *
 * @author m.leona
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable {
    
    private String direccion;
    
    
    private Date fecha;
    
  
    private String hora;
    
    private Integer precio;

    /**
    @return Dirección de la factura
    */
    public String getDireccion()
    {
        return direccion;
    }
    
  /**
   * 
   * @param pDireccion Dirección nueva de la factura
   */
    public void setDireccion(String pDireccion)
    {
        this.direccion=pDireccion;
    }
    
 /**
  * 
  * @return fecha de factura
  */
    public Date getFecha()
    {
        return fecha;
    }
    
   /**
    * 
    * @param pFecha Fecha nueva para la factura
    */
    public void setFecha(Date pFecha)
    {
        this.fecha=pFecha;
    }
    
   /**
    * 
    * @return Hora de la factura
    */
    public String getHora()
    {
        return hora;
    }
    
    /**
    @param pHora Nueva hora de la factura
    */
    public void setHora(String pHora)
    {
        this.hora=pHora;
    }
    
    /**
    @return precio total de la compra
    */
    public Integer getPrecio()
    {
        return precio;
    }
    
    /**
     * 
     * @param pPrecio Precio nuevo de la factura
     */
    public void setPrecio(Integer pPrecio)
    {
        this.precio = pPrecio;
    }
}
