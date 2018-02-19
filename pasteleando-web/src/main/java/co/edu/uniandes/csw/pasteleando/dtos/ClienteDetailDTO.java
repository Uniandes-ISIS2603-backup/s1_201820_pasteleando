/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;

/**
 * Clase que extiende de {@link ClienteDTO} para manejar la transformacion entre los objetos JSON
 * y las entidades de la base de datos.
 * 
 * @author mp.bayonal
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    /**
	 * Constructor por defecto
	 */
    public ClienteDetailDTO()
    {
    }
    
    /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de cliente a partir de la cual se construye el objeto
	 */
    public ClienteDetailDTO(ClienteEntity entity)
    {
        super(entity);
    }
    
     /**
     * transforma un DTO a un entity
     * @return la entidad construida a partir del DTO
     */
    public ClienteEntity toEntity()
    {
        ClienteEntity entity = super.toEntity();
        return entity;
    }
    
}
