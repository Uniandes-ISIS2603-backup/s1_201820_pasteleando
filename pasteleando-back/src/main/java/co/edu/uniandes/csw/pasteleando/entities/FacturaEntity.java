/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import co.edu.uniandes.csw.pasteleando.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStrategyValue;
/**
 * clase que representa una factura en la base de datos y permite su serializacion
 * @author m.leona
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable {
    
    private String direccion;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
  
    private String hora;
    
    @PodamIntValue(minValue = 1)
    private Integer precio;
 
    @PodamExclude
    @OneToOne
    private PedidoEntity pedido;
   
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
   
    
    /**
     * retorna la direccion de la factura
    *@return direccion
    */
    public String getDireccion()
    {
        return direccion;
    }
    
  /**
   * establece la direccion de la factura
   * @param pDireccion Dirección nueva de la factura
   */
    public void setDireccion(String pDireccion)
    {
        this.direccion=pDireccion;
    }
    
 /**
  * retorna la fecha de la factura
  * @return fecha de factura
  */
    public Date getFecha()
    {
        return fecha;
    }
    
   /**
    * establece la fecha de la factura
    * @param pFecha Fecha nueva para la factura
    */
    public void setFecha(Date pFecha)
    {
        this.fecha=pFecha;
    }
    
   /**
    * retorna la hora de la factura
    * @return Hora de la factura
    */
    public String getHora()
    {
        return hora;
    }
    
    /**
     * estblece la hora de la factura
    *@param pHora Nueva hora de la factura
    */
    public void setHora(String pHora)
    {
        this.hora=pHora;
    }
    
    /**
     * retorna el precio
    *@return precio total de la compra
    */
    public Integer getPrecio()
    {
        return precio;
    }
    
    /**
     * establece el pecio de la factura
     * @param pPrecio Precio nuevo de la factura
     */
    public void setPrecio(Integer pPrecio)
    {
        this.precio = pPrecio;
    }
    
    /**
     * retorna el pedido asociado a la factura
     * @return pedido
     */
    public PedidoEntity getPedido() {
        return pedido;
    }

    /**
     * establece el pedido asociado a la factura
     * @param pedido 
     */
    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
    
    /**
     * retorna el cliente asociado a la factura
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * establece el cliente asociado a la factura
     * @param cliente 
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
