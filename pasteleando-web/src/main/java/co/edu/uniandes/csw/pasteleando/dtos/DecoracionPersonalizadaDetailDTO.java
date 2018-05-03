/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.ArrayList;
import java.util.List;

/**
 ** *Un ejemplo de este DTO es:
 * <br>
*<pre>
*{
 *"color":"Azul",
 *  "imagen": "C:\Users\dc.cepeda\Desktop\cake.jpg",
 *  "estado": "aprobado",
 *"Decoracion":[{
 * "esPersonalizada: true
 * }]
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDetailDTO extends DecoracionPersonalizadaDTO
{
    
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
	public DecoracionPersonalizadaEntity toEntity( )
	{
		DecoracionPersonalizadaEntity entity = super.toEntity();
              
        return entity;
	}

}
