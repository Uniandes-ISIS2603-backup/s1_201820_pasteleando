/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;

/**
 *
 * @author ni.ramirez10
 */
public class PedidoDTO 
{
    private Long id;
    
    private boolean seRecogePasteleria; 
    
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
	public PedidoDTO( PedidoDTO pedidoDTO )
	{
		this.id = pedidoDTO.getId(); 
                this.seRecogePasteleria = pedidoDTO.getValueSeRecogePasteleria(); 
		this.estado = pedidoDTO.getEstado(); 
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
