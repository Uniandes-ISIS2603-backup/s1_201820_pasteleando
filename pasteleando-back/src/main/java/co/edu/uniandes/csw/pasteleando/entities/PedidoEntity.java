/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * clase que representa los pedidos en la base de datos y permite su serializacion
 * @author ni.ramirez10
 */
@Entity
public class PedidoEntity extends BaseEntity implements Serializable
{
    private String seRecogePasteleria;
    
    private String estado;
    
    @PodamExclude
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<PqrsEntity> pqrs = new ArrayList<>( );
    
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private CarritoEntity carrito = new CarritoEntity( );
    
    @PodamExclude
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<CalificacionEntity> calificaciones = new ArrayList<>( );
    
    @PodamExclude
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private FacturaEntity factura;
    
    /**
     * @return El valor del atributo seRecogePateleria
     */
    public String getValueSeRecogePasteleria( )
    {
        return seRecogePasteleria;
    }
    
    /**
     * @param valueRecogePasteleria El nuevo valor del atributo
     */
    public void setValueSeRecogePasteleria( String valueRecogePasteleria )
    {
        this.seRecogePasteleria = valueRecogePasteleria;
    }
    
    /**
     * @return El estado del pedido
     */
    public String getEstado( )
    {
        return estado;
    }
    
    /**
     * @param pEstado El nuevo estado del pedido
     */
    public void setEstado( String pEstado )
    {
        this.estado = pEstado;
    }
    
    /**
     * Obtiene la lista de pqrs.
     *
     * @return lista de pqrs.
     */
    public List<PqrsEntity> getPqrs()
    {
        return pqrs;
    }
    
    /**
     * Establece el valor de la lista de pqrs.
     *
     * @param pPqrs nuevo valor de la lista.
     */
    public void setPqrs(List<PqrsEntity> pPqrs)
    {
        this.pqrs = pPqrs;
    }
    
    /**
     * Obtiene el carrito
     * @return elemento de tipo carrito.
     */
    public CarritoEntity getCarrito()
    {
        return carrito;
    }
    
    /**
     * Establece un valor al carrito
     * @param pCarrito nuevo valor del elemento
     */
    public void setCarrito( CarritoEntity pCarrito)
    {
        this.carrito = pCarrito;
    }
    
    /**
     * Obtiene la lista de calificaciones
     * @return lista de calificaciones.
     */
    public List<CalificacionEntity> getCalificaciones()
    {
        return calificaciones;
    }
    
    /**
     * Establece el valor de la lista de calificaciones.
     * @param pCalificacion nuevo valor de la lista.
     */
    public void setCalificacion(List<CalificacionEntity> pCalificacion)
    {
        this.calificaciones = pCalificacion;
    }
    
    /**
     * Obtiene la factura
     * @return elemento de tipo factura.
     */
    public FacturaEntity getFactura()
    {
        return factura;
    }
    
    /**
     * Establece un valor al a factura
     * @param pFactura nuevo valor del elemento
     */
    public void setFactura( FacturaEntity pFactura)
    {
        this.factura = pFactura;
    }
    
    
    
}
