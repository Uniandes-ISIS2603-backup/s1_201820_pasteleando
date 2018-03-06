/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link PqrsDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del pqrs vaya a la documentacion de {@link PqrsDTO}
 * 
 * @author ni.ramirez10
 */

public class PqrsDetailDTO extends PqrsDTO
{
    
        private List<PqrsDTO> pqrs; 
            
        private ClienteDTO cliente;
        
        private PedidoDTO pedido;
        
        /**
	 * Constructor por defecto
	 */
	public PqrsDetailDTO( )
	{
            super( ); 
	}
        
        public PqrsDetailDTO(PqrsEntity entity)
        {
            super(entity);
            if(entity.getCliente() != null)
            {
                this.cliente = new ClienteDTO(entity.getCliente());
            }
            if(entity.getCliente()==null)
            {
                entity.setCliente(null);
            }
            if(entity.getPedido() != null)
            {
                this.pedido = new PedidoDTO(entity.getPedido());
            }
            if(entity.getPedido()==null)
            {
                entity.setPedido(null);
            }
        }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

        
         /**
         * Obtiene la lista de pqrs
         * @return Los pqrs
         */
         public List<PqrsDTO> getPqrs() 
         {
                return pqrs;
         }

        /**
         * Modifica la lista de pqrs
         * @param pPqrs Los pqrs a establecer
         */
         public void setPqrs(List<PqrsDTO> pPqrs) 
         {
                this.pqrs = pPqrs;
         }

	

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public PqrsEntity toEntity( )
	{
		PqrsEntity entity = super.toEntity( );                
		if(this.getCliente() != null)
                {
                    entity.setCliente(this.getCliente().toEntity());
                }
                if(this.getPedido() != null)
                {
                    entity.setPedido(this.getPedido().toEntity());
                }
                return entity;
	}
        
       
    
}
