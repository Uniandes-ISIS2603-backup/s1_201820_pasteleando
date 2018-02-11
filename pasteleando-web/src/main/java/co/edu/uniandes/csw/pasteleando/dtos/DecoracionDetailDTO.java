/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionDetailDTO extends DecoracionDTO
{

	/**
	 * Constructor por defecto
	 */
	public DecoracionDetailDTO( )
	{
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public DecoracionDetailDTO( DecoracionEntity entity )
	{
		super( entity );
	}

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public DecoracionEntity toEntity( )
	{
		DecoracionEntity decoracionEntity = super.toEntity( );
		return decoracionEntity;
	}

}
