/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link ClienteDTO} para manejar las relaciones entre
 * las ClienteDTO JSON y otros DTOs. Para conocer el contenido de la clase vaya
 * a la documentacion de {@link ClienteDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 *
 *
 * <pre>
 * {
 *    "id": long ,
 *    "idCarrito":Integer ,
 *    "formaPagoActual": String,
 *    "numeroPuntos":Integer,
 *    "tiposPago": String[],
 *    "calificaciones": [
 *                       {
 *                         "id": long,
 *                         "puntaje": Integer,
 *                         "comentario": String
 *                        } ,
 *                        {
 *                          "id": long,
 *                          "puntaje": Integer,
 *                          "comentario": String
 *                         }
 *                       ] ,
 *      "pqrs": [
 *                {
 *                  "tipo": number,
 *                  "idSolicitud": number,
 *                  "idCliente": number,
 *                  "fecha": string,
 *                  "estado": string
 *                 } ,
 *                 {
 *                  "tipo": number,
 *                  "idSolicitud": number,
 *                  "idCliente": number,
 *                  "fecha": string,
 *                  "estado": string
 *                 }
 *              ] ,
 *      "carrito": {
 *                  "id": long ,
 *                  "idCarrito":Integer ,
 *                  "formaPagoActual": String,
 *                  "tiposPago": String[]
 *                 } ,
 *
 *  }
 * </pre> Por ejemplo un cliente se representa asi:<br>
 *
 * <pre>
 *    * {
 *    "id": 555555 ,
 *    "idCarrito":3578 ,
 *    "formaPagoActual": "tarjeta credito",
 *    "tiposPago": [ "Tarjeta Credito" , "Tarjeta Debito" , "Pago Efectivo" ],
 *    "calificaciones": [
 *                       {
 *                         "id": 748696,
 *                         "puntaje": 10,
 *                         "comentario": "La atencion fue excelente"
 *                        } ,
 *                        {
 *                          "id": 4566,
 *                          "puntaje": 5,
 *                          "comentario": "La atencion fue muy mala"
 *                         }
 *                       ] ,
 *      "pqrs": [
 *                {
 *                  "tipo": 4,
 *                  "idSolicitud": 235,
 *                  "idCliente": 5788,
 *                  "fecha": "5 de agosto",
 *                  "estado": "en revision"
 *                 } ,
 *                 {
 *                  "tipo": 5,
 *                  "idSolicitud": 2456,
 *                  "idCliente": 98765,
 *                  "fecha": "8 de agosto",
 *                  "estado": "revisado"
 *                 }
 *              ] ,
 *      "carrito": {
 *                  "id": 26748957,
 *                   "cantidad" : 4,
 *                   "precio": 280,9,
 *                   "articulos : [ {PastelDTO},...]
 *                 } ,
 *      "tarjeta": {
 *                  "numeropuntos":234
 *                 }
 *  }
 * </pre>
 *
 * @author mpbayonal
 */
public class ClienteDetailDTO extends ClienteDTO {

    private CarritoDTO carrito;
    private PedidoDTO pedido;

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    private List<PqrsDTO> pqrs;
    private List<CalificacionDTO> calificaciones;
    private List<FacturaDTO> facturas;

    private List<DecoracionPersonalizadaDTO> decoraciones;

    public List<DecoracionPersonalizadaDTO> getDecoraciones() {
        return decoraciones;
    }

    public void setDecoraciones(List<DecoracionPersonalizadaDTO> decoraciones) {
        this.decoraciones = decoraciones;
    }

    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * Constructor por defecto
     */
    public ClienteDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de cliente a partir de la cual se construye el
     * objeto
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        
        if(entity.getPedido() != null)
        {
            this.pedido = new PedidoDTO(entity.getPedido());
        }
        else{
            
            this.pedido = null;
        }
         
        if (entity.getCalificaciones() != null) {
            this.calificaciones = new ArrayList<CalificacionDTO>();
            for (int i = 0; i < entity.getCalificaciones().size(); i++) {
                CalificacionDTO nuevo = new CalificacionDTO(entity.getCalificaciones().get(i));
                this.calificaciones.add(nuevo);
            }
        } else {
            this.calificaciones = null;

        }

        if (entity.getPqrs() != null) {
            this.pqrs = new ArrayList<PqrsDTO>();
            for (int i = 0; i < entity.getPqrs().size(); i++) {
                PqrsDTO nuevo = new PqrsDTO(entity.getPqrs().get(i));
                this.pqrs.add(nuevo);
            }

        } else {
            this.pqrs = null;
        }

        if (entity.getDecoraciones() != null) {
            this.decoraciones = new ArrayList<DecoracionPersonalizadaDTO>();
            for (int i = 0; i < entity.getPqrs().size(); i++) {
                DecoracionPersonalizadaDTO nuevo = new DecoracionPersonalizadaDTO(entity.getDecoraciones().get(i));
                this.decoraciones.add(nuevo);
            }

        } else {
            this.pqrs = null;
        }
        if (entity.getCarrito() != null) {
            this.carrito = new CarritoDTO(entity.getCarrito());
        } else {
            this.carrito = null;
        }

        if (entity.getFacturas() != null) {
            this.facturas = new ArrayList<FacturaDTO>();
            for (int i = 0; i < entity.getFacturas().size(); i++) {
                FacturaDTO nuevo = new FacturaDTO(entity.getFacturas().get(i));
                this.facturas.add(nuevo);
            }

        } else {
            this.pqrs = null;
        }

    }

    /**
     * transforma un DTO a un entity
     *
     * @return la entidad construida a partir del DTO
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
    if (this.getPedido() != null) {
            entity.setPedido(this.getPedido().toEntity());
        }
         else{
         entity.setPedido(null);}
        if (this.calificaciones != null) {
            entity.setCalificaciones(new ArrayList<CalificacionEntity>());
            for (int i = 0; i < this.calificaciones.size(); i++) {
                CalificacionEntity nuevo = calificaciones.get(i).toEntity();
                entity.getCalificaciones().add(nuevo);
            }
        } else {
            entity.setCalificaciones(null);

        }

        if (this.pqrs != null) {
            entity.setPqrs(new ArrayList<PqrsEntity>());
            for (int i = 0; i < this.pqrs.size(); i++) {
                PqrsEntity nuevo = this.pqrs.get(i).toEntity();
                entity.getPqrs().add(nuevo);
            }

        } else {
            entity.setPqrs(null);
        }

        if (this.carrito != null) {
            entity.setCarrito(this.carrito.toEntity());
        } else {
            entity.setCarrito(null);
        }

        if (this.facturas != null) {
            entity.setFacturas(new ArrayList<FacturaEntity>());
            for (int i = 0; i < this.facturas.size(); i++) {
                FacturaEntity nuevo = this.facturas.get(i).toEntity();
                entity.getFacturas().add(nuevo);
            }

        } else {
            entity.setPqrs(null);
        }

        if (this.decoraciones != null) {
            entity.setDecoraciones(new ArrayList<DecoracionPersonalizadaEntity>());
            for (int i = 0; i < this.decoraciones.size(); i++) {

                DecoracionPersonalizadaEntity y = new DecoracionPersonalizadaEntity();
                DecoracionPersonalizadaEntity b = new DecoracionPersonalizadaEntity();
                y = this.decoraciones.get(i).toEntity(b);

                entity.getDecoraciones().add(y);
            }

        } else {
            entity.setPqrs(null);
        }

        return entity;
    }

    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * Modifica el carrito asociado a este cliente.
     *
     * @param carrito the promocion to set
     */
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    public List<PqrsDTO> getPqrs() {
        return pqrs;
    }

    /**
     * Modifica la pqrs asociada a este cliente.
     *
     * @param pqrs the promocion to set
     */
    public void setPqrs(List<PqrsDTO> pqrs) {
        this.pqrs = pqrs;
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones asociadas a este cliente.
     *
     * @param calificaciones the promocion to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

}
