/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import java.io.Serializable;

/**
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * 
 * <pre>
 *   {
 *      "color": String,
 *      "imagen": String,
 *      "estado": String
 *   }
 * </pre>
 * 
 * Por ejemplo una decoracion se representa asi:<br>
 * 
 * <pre>
 * 
 *{
 *  "color":Azul,
 *  "imagen": "C:\Users\dc.cepeda\Desktop\cake.jpg",
 *  "estado": enviadoa
 * }
 * 
 * </pre>
 * 
 */
/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDTO extends DecoracionDTO implements Serializable
{
    
    /**
     * Atributo que modela el color de la DecoracionPersonalizada
     */
    private String color;
     /**
     * Atributo que modela el estado de la DecoracionPersonalizada
     */
    private String estado;
    /**
     * Atributo que modela imagen de la DecoracionPersonalizada
     */
    private String imagen;
    /**
     * Atributo que modela el id de la DecoracionPersonalizada
     */
    private Long id;

    /**
	 * Constructor por defecto
	 */
	public DecoracionPersonalizadaDTO( )
	{
	}

	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param decoracionPersonalizadaEntity: Es la entidad que se va a convertir a DTO
	 */
	public DecoracionPersonalizadaDTO( DecoracionPersonalizadaEntity decoracionPersonalizadaEntity )
	{
		this.color = decoracionPersonalizadaEntity.getColor();
		this.estado = decoracionPersonalizadaEntity.getEstado();
		this.imagen = decoracionPersonalizadaEntity.getImagen();
                this.id=decoracionPersonalizadaEntity.getId();

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
	 * @return La imagen de la entidad DecoracionPersonalizada
	 */
    public String getImagen() {
        return imagen;
    }
        /**
	 * @param imagen La nueva imagen de la entidad DecoracionPersonalizada
	 */
    public void setImagen(String imagen) {
        this.imagen = imagen;
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
	 * @return Un Entity con los valores del DTO
	 */
	public DecoracionPersonalizadaEntity toEntity( )
	{
		DecoracionPersonalizadaEntity entity = new DecoracionPersonalizadaEntity( );
		entity.setColor( this.color );
		entity.setImagen( this.imagen );
		entity.setEstado( this.estado );
		return entity;
	}
        
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
