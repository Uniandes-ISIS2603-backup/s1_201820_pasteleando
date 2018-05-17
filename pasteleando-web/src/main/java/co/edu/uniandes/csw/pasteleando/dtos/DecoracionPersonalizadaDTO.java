/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import java.io.Serializable;

/**
 * Por ejemplo una decoracion se representa asi:
 * <br>
 *
 * <pre>
 *
 *{
 *  "color":"Azul",
 *  "imagen": "C:\Users\dc.cepeda\Desktop\cake.jpg",
 *  "estado": "aprobado"
 * }
 *
 * </pre>
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDTO extends DecoracionDTO implements Serializable {

    /**
     * Atributo que modela el color de la DecoracionPersonalizada
     */
    private String color;
    /**
     * Atributo que modela el estado de la DecoracionPersonalizada
     */
    private String estado;

    private Integer peso;

    private Long id;

    /**
     * @return El id de la entidad DecoracionPersonalizada
     */
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    /**
     * Constructor por defecto
     */
    public DecoracionPersonalizadaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param decoracionPersonalizadaEntity: Es la entidad que se va a convertir
     * a DTO
     */
    public DecoracionPersonalizadaDTO(DecoracionPersonalizadaEntity decoracionPersonalizadaEntity) {
        super(decoracionPersonalizadaEntity);
        this.id = decoracionPersonalizadaEntity.getId();
        this.color = decoracionPersonalizadaEntity.getColor();
        this.estado = decoracionPersonalizadaEntity.getEstado();
        this.peso = decoracionPersonalizadaEntity.getPeso();

    }

    /**
     * @return La fecha de la entidad DecoracionPersonalizada
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color El nuevo color de la entidad DecoracionPersonalizada
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return El estado de la entidad DecoracionPersonalizada
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado El nuevo estado de la entidad DecoracionPersonalizada
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Convertir DTO a Entity
     *
     * @param entity entidad de la Decoracion Personalizada
     * @return Un Entity con los valores del DTO
     */
    public DecoracionPersonalizadaEntity toEntity(DecoracionPersonalizadaEntity entity) {

        super.toEntity(entity);
        entity.setId(this.id);
        entity.setColor(this.color);
        entity.setEstado(this.estado);
        entity.setPeso(this.peso);
        return entity;
    }
}
