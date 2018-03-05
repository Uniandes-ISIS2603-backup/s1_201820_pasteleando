/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import java.io.Serializable;

/**
 * PedidoDTO Objeto de transferencia de datos de pedido. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "seRecogePasteleria": string,
 *      "estado": string
 *   }
 * </pre>
 * Por ejemplo un pedido se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 1508,
 *      "seRecogePasteleria: "si",
 *      "estado": "Despachado"
 *   }
 *
 * </pre>
 * @author ni.ramirez10
 */
public class PedidoDTO implements Serializable
{
    private Long id;
    
    private String seRecogePasteleria; 
    
    private String estado; 
    
     /**
	 * Constructor por defecto
	 */
	public PedidoDTO( )
	{
            
	}
 /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param pedidoDTO: Es la entidad que se va a convertir a DTO
	 */
	public PedidoDTO( PedidoEntity pedidoEntity )
	{
		this.id = pedidoEntity.getId(); 
                this.seRecogePasteleria = pedidoEntity.getValueSeRecogePasteleria(); 
		this.estado = pedidoEntity.getEstado(); 
	}
        
       /**
	 * @return El ID del pedido
	 */
	public Long getId( )
	{
		return id;
	}
        
        /**
	 * @param id El nuevo ID
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
        
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
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public PedidoEntity toEntity( )
	{
		PedidoEntity entity = new PedidoEntity( );
		entity.setId(this.id);
		entity.setValueSeRecogePasteleria(this.seRecogePasteleria);
                entity.setEstado(this.estado);
		return entity;
	}
    
}
