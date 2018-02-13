/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import java.util.Date;



/**
 *Un ejemplo de este DTO es:
 * <br>
*<pre>
*{
 *"id":1907, 
 *"direccion":"Calle 127 #47-48",
 *"fecha":"13/02/18", 
 *"hora":"22:00",
 *"precio":"55,000 COP"
*}
*</pre>
 * @author m.leona
 */
public class FacturaDTO {
    
    //Atributos
    
    //id en la base de datos
    /**
     * Referencia a la base de datos
     */
    private Long id;
    
    //Dirección a la cual se hace el comprobante de pago
    /**
     * Dirección de la factura
     */
    private String direccion;
    
    //Fecha en que se facturó formato dd/mm/yy
    /**
     * Fecha de la factura
     */
    private Date fecha;
    
    //Hora del comprobante de pago dado en HH:MM
    /**
     * Hora de la factura
     */
    private String hora;
    
    //Precio total de la factura dado en COP
    /**
     * Precio de la factura
     */
    private Integer precio;
    
    //Constructor
    
    /**
     * constructor
     */
    public FacturaDTO()
    {
     
    }
    
    //Metodos
    
    /**
    @return id al que está asociado
    */
    public Long getId()
    {
        return id;
    }
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
     * @param id Referencia nueva a la base de datos
    */
    public void setId(Long id)
    {
     this.id = id;   
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
