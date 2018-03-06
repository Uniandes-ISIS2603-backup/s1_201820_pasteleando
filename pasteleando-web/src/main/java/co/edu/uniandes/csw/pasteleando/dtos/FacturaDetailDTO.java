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
 *"TarjetaPuntos":[{
 * "NumeroPuntos: 0"
 * }]
*}
*</pre>
* 
 * @author m.leona
 */
public class FacturaDetailDTO extends FacturaDTO {
  
        TarjetaPuntosDTO tarjeta;
        
        PedidoDTO pedido;
        
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
                if(entity.getTarjetaPuntos() != null)
                {
                    this.tarjeta = new TarjetaPuntosDTO(entity.getTarjetaPuntos());
                }
                if(entity.getTarjetaPuntos() == null)
                {
                    entity.setTarjetaPuntos(null);
                }
            }
        }
        
        @Override
        public FacturaEntity toEntity()
        {
            FacturaEntity facturaE = super.toEntity();
            if(this.getTarjeta() != null)
            {
                facturaE.setTarjetaPuntos(this.getTarjeta().toEntity());
            }
            if(this.getPedido() != null)
            {
                facturaE.setPedido(this.getPedido().toEntity());
            }
            return facturaE;
        }

    public TarjetaPuntosDTO getTarjeta() {
        return tarjeta;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setTarjeta(TarjetaPuntosDTO tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }
        
       
        

}
