/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;

/**
 * Clase que extiende de {@link PqrsDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del pqrs vaya a la documentacion de {@link PqrsDTO}
 * 
 * @author ni.ramirez10
 */
public class PqrsDetailDTO extends PqrsDTO
{
    /**
	 * Constructor por defecto
	 */
	public PqrsDetailDTO( )
	{
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public PqrsDetailDTO( PqrsDetailDTO entity )
	{
		super( entity );
	}


	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public PqrsEntity toEntity( )
	{
		PqrsEntity pedidoEntity = super.toEntity( );
		return pedidoEntity;
	}
    
}
