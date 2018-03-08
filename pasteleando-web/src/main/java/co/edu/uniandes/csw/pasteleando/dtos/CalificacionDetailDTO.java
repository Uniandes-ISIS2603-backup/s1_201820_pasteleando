package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;

/**
 * Clase que extiende de {@link CalificacionDTO} para manejar la transformacion entre los objetos JSON
 * y las entidades de la base de datos.
* <pre> 
 * {
 *  "id": long,
 *  "puntaje": Integer,
 *  "comentario": String, 
 *  "cliente": {
 *              "id": long ,
 *              "idCarrito":Integer ,
 *              "formaPagoActual": String,
 *              "tiposPago": String[]
 *             }, 
 * "decoracion": {
 *                  "fechaAgregado": Date,
 *                  "foto": String
 *              }
 * }
 * </pre>
 * Una calificacion se representaria asi:
 * <pre>
 * {
 * "id": 245333,
 * "puntaje": 5,
 * "comentario", "La atencion fue excelente",
 * "cliente": {
 *              "id": 245678905,
 *              "idCarrito": 233456,
 *              "formaPagoActual": "Tarjeta Credito",
 *              "tiposPago": [ "Tarjeta Credito" , "Tarjeta Debito" , "Pago Efectivo" ]
 * 
 *              }, 
 * "decoracion": {
 *                  "fechaAgregado":12/08/18,
 *                  "foto": "C:\Users\dc.cepeda\Desktop\cake.jpg"
 *                 }
 * }
 * 
 * </pre>
 * @author mp.bayonal
 */

public class CalificacionDetailDTO extends CalificacionDTO
{
    private ClienteDTO cliente;
    private DecoracionDTO decoracion;
    private PedidoDTO pedido;
    
    /**
     * Constructor por defecto
     */
    public CalificacionDetailDTO()
    {
        super();
    }
     /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
    public CalificacionDetailDTO(CalificacionEntity entity)
    {
        super(entity);
        if(entity.getCliente() != null)
        {
            this.cliente = new ClienteDTO(entity.getCliente());
        }
        else
        {
           entity.setCliente(null);
        }
        
        if(entity.getDecoracion() != null)
        {
            this.decoracion = new DecoracionDTO(entity.getDecoracion());
        }
        else
        {
            entity.setDecoracion(null);
        }
        if(entity.getPedido() != null)
        {
            this.pedido = new PedidoDTO(entity.getPedido());
        }
        else
        {
            entity.setPedido(null);
        }
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }
    
    

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public DecoracionDTO getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(DecoracionDTO decoracion) {
        this.decoracion = decoracion;
    }
    /**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
    @Override
    public CalificacionEntity toEntity()
    {
        CalificacionEntity entity = super.toEntity();
        if(this.cliente != null)
        {
            entity.setCliente(this.cliente.toEntity());
        }
        if(this.decoracion != null)
        {
            entity.setDecoracion(this.decoracion.toEntity());
        }
        if(this.pedido != null)
        {
            entity.setPedido(this.pedido.toEntity());
        }
       return entity;
    }
}

