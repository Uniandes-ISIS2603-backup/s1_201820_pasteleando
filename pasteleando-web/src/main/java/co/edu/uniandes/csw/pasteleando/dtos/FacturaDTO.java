/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import java.util.Date;
import java.io.Serializable;



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
public class FacturaDTO implements Serializable {
    
    //Atributos
    
    /**
     * Referencia a la base de datos
     */
    private Long id;
    
    /**
     * Dirección de la factura
     */
    private String direccion;
    
    /**
     * Fecha de la factura
     */
    private Date fecha;
    
    /**
     * Hora de la factura
     */
    private String hora;
    
    /**
     * Precio de la factura
     */
    private Integer precio;
    
    //Constructor
    
    /**
     * constructor por defecto
     */
    public FacturaDTO()
    {
     /**
      * vacío
      */
    }
    
    /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param FacturaEntity: Es la entidad que se va a convertir a DTO
	 */
	public FacturaDTO( FacturaEntity facturaEntity )
	{
            if( facturaEntity != null)
            {
		this.direccion = facturaEntity.getDireccion();
		this.fecha=facturaEntity.getFecha();
                this.hora=facturaEntity.getHora();
                this.id=facturaEntity.getId();
                this.precio=facturaEntity.getPrecio();
            }
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
    
    /**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public FacturaEntity toEntity( )
	{
		FacturaEntity entity = new FacturaEntity( );
		entity.setDireccion(this.direccion);
		entity.setFecha(this.fecha );
                entity.setHora(this.hora);
                entity.setId(this.id);
                entity.setPrecio(this.precio);
		return entity;
	}
}
