/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import java.util.List;

/**
 *
 * @author dc.cepeda
 */

public class DecoracionDetailDTO extends DecoracionDTO
{
     /**
     * Atributo que modela la colecci{on de decoraciones 
     */
        private List <DecoracionDTO> decoracion;
         /**
     * Atributo que modela la coleccion de pasteles 
     */
        private List <PastelDTO> pastel;
        
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
