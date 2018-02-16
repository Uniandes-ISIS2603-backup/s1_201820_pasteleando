/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import java.util.List;

/**
 *
 * @author jf.garcia
 */
public class DecoracionCatalogoDetailDTO extends DecoracionCatalogoDTO
{
        private List <DecoracionCatalogoDTO> decoracion;
        private List <PromocionDTO> promocion;
        
	/**
	 * Constructor por defecto
	 */
	public DecoracionCatalogoDetailDTO( )
	{
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public DecoracionCatalogoDetailDTO( DecoracionCatalogoEntity entity )
	{
		super( entity );
	}

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public DecoracionCatalogoEntity toEntity( )
	{
		DecoracionCatalogoEntity decoracionCatalogoEntity = super.toEntity( );
		return decoracionCatalogoEntity;
	}

}

