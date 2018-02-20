/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author ni.ramirez10
 */
public class PedidoEntity extends BaseEntity implements Serializable
{
    private boolean seRecogePasteleria; 
    
    private String estado; 
    
    private List<PedidoEntity> pedidos = new ArrayList<PedidoEntity>();
        
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
         * Obtiene la lista de pedidos.
         *
         * @return lista de pedidos.
         */
         public List<PedidoEntity> getPedidos() 
         {
             return pedidos;
         }

        /**
         * Establece el valor de la lista de pedidos.
         *
         * @param pPedidos nuevo valor de la lista.
         */
         public void setPedidos(List<PedidoEntity> pPedidos) 
         {
            this.pedidos = pPedidos;
         }
    
    
}
