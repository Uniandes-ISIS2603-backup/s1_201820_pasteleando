/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author m.leona
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable {
    
    @OneToOne
    @PodamExclude
    private PedidoEntity pedido;
    
    @ManyToOne
    @PodamExclude
    private TarjetaPuntosEntity tarjetaPuntos;

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public void setTarjetaPuntos(TarjetaPuntosEntity tarjetaPuntos) {
        this.tarjetaPuntos = tarjetaPuntos;
    }

    public TarjetaPuntosEntity getTarjetaPuntos() {
        return tarjetaPuntos;
    }
    
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
