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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ni.ramirez10
 */
@Entity
public class PedidoEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    private boolean seRecogePasteleria; 
    
    private String estado; 
    
    private List<PedidoEntity> pedido = new ArrayList<PedidoEntity>( );
    
    @ManyToOne
    private List<PqrsEntity> pqrs = new ArrayList<PqrsEntity>( );
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private CarritoEntity carrito = new CarritoEntity( ); 
    
    @ManyToOne
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>( ); 
    
    private FacturaEntity factura = new FacturaEntity( ); 
        
         /**
	 * @return El valor del atributo seRecogePateleria
	 */
	public boolean getValueSeRecogePasteleria( )
	{
		return seRecogePasteleria;
	}
        
         /**
	 * @param valueRecogePasteleria El nuevo valor del atributo
	 */
	public void setValueSeRecogePasteleria( boolean valueRecogePasteleria )
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
         
         
         public List<PedidoEntity> getPedidos( )
         {
             return pedido; 
         }
         
         public void setPedidos( List<PedidoEntity> pPedido)
         {
             this.pedido = pPedido; 
         }
    
    
}
