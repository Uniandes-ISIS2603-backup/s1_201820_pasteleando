/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;

/**
 *
 * @author jf.garcia
 */
public class DecoracionCatalogoDTO {
    
    private String categoria;
        
    /**
	 * Constructor por defecto
	 */
	public DecoracionCatalogoDTO( )
	{
	}

	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param decoracionCatalogoEntity: Es la entidad que se va a convertir a DTO
	 */
	public DecoracionCatalogoDTO( DecoracionCatalogoEntity decoracionCatalogoEntity )
	{
		this.categoria = decoracionCatalogoEntity.getCategoria();

	}

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public DecoracionCatalogoEntity toEntity( )
	{
		DecoracionCatalogoEntity entity = new DecoracionCatalogoEntity( );
		entity.setCategoria( this.categoria );
		return entity;
	}
}
