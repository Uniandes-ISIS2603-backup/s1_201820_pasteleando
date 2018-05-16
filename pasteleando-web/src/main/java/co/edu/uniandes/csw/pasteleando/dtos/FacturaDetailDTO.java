/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;


/**
 * *Un ejemplo de este DTO es:
 * <br>
*<pre>
*{
 *"id":1907, 
 *"direccion":"Calle 127 #47-48",
 *"fecha":"13/02/18", 
 *"hora":"22:00",
 *"precio":"55,000 COP",
*}
*</pre>
* 
 * @author m.leona
 */

public class FacturaDetailDTO extends FacturaDTO {
  
        
        PedidoDTO pedido;
        
        transient ClienteDTO cliente;
        
	/**
	 * Constructor por defecto
	 */
	public FacturaDetailDTO( )
	{
            super();
	}
        
        /**
         * Constructor para transformar un Entity a un DTO
         * @param entity la entidad de la cual se construye el DTO
         */
        public FacturaDetailDTO(FacturaEntity entity)
        {
            super(entity);
            if(entity != null)
            {
                if(entity.getPedido() != null)
                {
                    this.pedido = new PedidoDTO(entity.getPedido());
                }
                if(entity.getPedido() == null)
                {
                    entity.setPedido(null);
                }
                if(entity.getCliente() != null)
                {
                    this.cliente = new ClienteDTO(entity.getCliente());
                }
                if(entity.getCliente() == null)
                {
                    entity.setCliente(null);
                }
                
            }
            
        }
        
        /**
	 * Convertir DTO a Entity
	 * @return Un Entity con los valores del DTO
	 */
        
        @Override
        public FacturaEntity toEntity()
        {
            FacturaEntity facturaE = super.toEntity();
           
            if(this.getPedido() != null)
            {
                facturaE.setPedido(this.getPedido().toEntity());
            }
            if(this.getCliente() != null)
            {
                facturaE.setCliente(this.getCliente().toEntity());
            }
            return facturaE;
        }


        /**
         * Obtiene el pedido
         * @return El pedido
         */
         public PedidoDTO getPedido() {
             return pedido;
         }
         
        /**
         * Define un cliente
         * @return El cliente
         */
        public void setCliente(ClienteDTO cliente) {
            this.cliente = cliente;
        }

        /**
         * Obtiene el cliente
         * @return El cliente
         */
        public ClienteDTO getCliente() {
            return cliente;
        }

        /**
         * Define un pedido
         * @return El pedido
         */ 
        public void setPedido(PedidoDTO pedido) {
            this.pedido = pedido;
        }
        
       
        

}
