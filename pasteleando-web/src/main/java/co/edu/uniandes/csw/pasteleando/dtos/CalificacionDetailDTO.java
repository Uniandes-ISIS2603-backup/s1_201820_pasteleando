package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;

/**
 * Clase que extiende de {@link CalificacionDTO} para manejar la transformacion entre los objetos JSON
 * y las entidades de la base de datos.
 * 
 * @author mp.bayonal
 */

public class CalificacionDetailDTO extends CalificacionDTO
{
    
    public CalificacionDetailDTO()
    {
    }
     /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
    public CalificacionDetailDTO(CalificacionEntity entity)
    {
        super(entity);
    }
    /**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
    @Override
    public CalificacionEntity toEntity()
    {
        CalificacionEntity entity = super.toEntity();
       return entity;
    }
}

