/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDTO {
    
    private String color;
    
    private String imagen;
    
    private String estado;
    
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

	}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

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
}
