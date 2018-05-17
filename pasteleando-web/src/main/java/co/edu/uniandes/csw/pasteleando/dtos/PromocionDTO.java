/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import java.io.Serializable;

/**
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 *
 * <pre>
 *      {
 *      "id": number,
 *      "cantidad": number
 *      }
 * </pre>
 *
 * Por ejemplo una promoción se representa asi:<br>
 *
 * <pre>
 *      {
 *      "id": number,
 *      "cantidad": number
 *      }
 *
 * </pre>
 *
 */
/**
 *
 * @author jf.garcia
 */
public class PromocionDTO implements Serializable {

    /**
     * Atributo que modela el porcentaje de la promocion.
     */
    private Long id;

    /**
     * Atributo que modela el porcentaje de la promocion.
     */
    private Integer cantidad;

    /**
     * Constructor vacio
     */
    public PromocionDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public PromocionDTO(PromocionEntity entity) {
        this.cantidad = entity.getCantidad();
        this.id = entity.getId();

    }

    /**
     * Devuelve el ID de la promoción.
     *
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la promoción.
     *
     * @param id El id nuevo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve la cantidad de la promoción.
     *
     * @return la cantidad.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Modifica la cantidad de la promoción.
     *
     * @param cantidad La cantidad nueva.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PromocionEntity toEntity() {
        PromocionEntity entity = new PromocionEntity();
        entity.setCantidad(this.cantidad);
        entity.setId(this.id);
        return entity;
    }
}
