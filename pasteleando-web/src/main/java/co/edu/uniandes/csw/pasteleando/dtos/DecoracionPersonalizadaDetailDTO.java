/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import java.util.List;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDetailDTO extends DecoracionPersonalizadaDTO
{
     /**
     * Atributo que modela la coleccion de la Decoraciones
     */
    private List <DecoracionDTO> decoracion;

 /**
     * Atributo que modela la coleccion de la Decoraciones Personalizadas
     */    private List <DecoracionPersonalizadaDTO> decoracionPersonalizada;
        

	/**
	 * Constructor por defecto
	 */
	public DecoracionPersonalizadaDetailDTO( )
	{
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public DecoracionPersonalizadaDetailDTO( DecoracionPersonalizadaEntity entity )
	{
		super( entity );
	}

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public DecoracionPersonalizadaEntity toEntity( )
	{
		DecoracionPersonalizadaEntity decoracionPersonalizadaEntity = super.toEntity( );
		return decoracionPersonalizadaEntity;
	}

    public List<DecoracionDTO> getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(List<DecoracionDTO> decoracion) {
        this.decoracion = decoracion;
    }

    public List<DecoracionPersonalizadaDTO> getDecoracionPersonalizada() {
        return decoracionPersonalizada;
    }

    public void setDecoracionPersonalizada(List<DecoracionPersonalizadaDTO> decoracionPersonalizada) {
        this.decoracionPersonalizada = decoracionPersonalizada;
    }
}
