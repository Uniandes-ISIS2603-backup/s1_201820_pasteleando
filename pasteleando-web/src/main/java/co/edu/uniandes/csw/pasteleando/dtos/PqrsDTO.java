/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.io.Serializable;

/**
 * PqrsDTO Objeto de transferencia de datos de pqrs. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tipo": number,
 *      "idSolicitud": number,
 *      "idCliente": number,
 *      "fecha": string,
 *      "estado": string
 *   }
 * </pre> Por ejemplo un pqrs se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "tipo": 01,
 *      "idSolicitud": 408,
 *      "idCliente": 11098,
 *      "fecha": "20/02/2018",
 *      "estado": "Esperando respuesta"
 *   }
 *
 * </pre>
 *
 * @author ni.ramirez10
 */
public class PqrsDTO implements Serializable {

    private Integer tipo;

    private Integer idSolicitud;

    private Integer idCliente;

    private String fecha;

    private String estado;

    /**
     * Constructor por defecto
     */
    public PqrsDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pqrsEntity: Es la entidad que se va a convertir a DTO
     */
    public PqrsDTO(PqrsEntity pqrsEntity) {
        this.tipo = pqrsEntity.getTipo();
        this.idSolicitud = pqrsEntity.getIdSolicitud();
        this.idCliente = pqrsEntity.getIdCliente();
        this.fecha = pqrsEntity.getFecha();
        this.estado = pqrsEntity.getEstado();
    }

    /**
     * @return El tipo de la solicitud
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param pTipo El nuevo tipo de solicitud
     */
    public void setTipo(Integer pTipo) {
        this.tipo = pTipo;
    }

    /**
     * @return El id de la solicitud
     */
    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param pIdSolicitud El nuevo id de la solicitud
     */
    public void setIdSolicitud(Integer pIdSolicitud) {
        this.idSolicitud = pIdSolicitud;
    }

    /**
     * @return El id del cliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param pIdCliente El nuevo id del cliente
     */
    public void setIdCliente(Integer pIdCliente) {
        this.idCliente = pIdCliente;
    }

    /**
     * @return La fecha de la solicitud
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param pFecha La nueva fecha se la solicitud
     */
    public void setFecha(String pFecha) {
        this.fecha = pFecha;
    }

    /**
     * @return El estado de la solicitud
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param pEstado El nuevo estado de la solicitud
     */
    public void setEstado(String pEstado) {
        this.estado = pEstado;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PqrsEntity toEntity() {
        PqrsEntity entity = new PqrsEntity();
        entity.setTipo(this.tipo);
        entity.setIdSolicitud(this.idSolicitud);
        entity.setIdCliente(this.idCliente);
        entity.setFecha(this.fecha);
        entity.setEstado(this.estado);
        return entity;
    }

}
