/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link PqrsDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del pqrs vaya a la documentacion de {@link PqrsDTO}
 * 
 * @author ni.ramirez10
 */

public class PqrsDetailDTO extends PqrsDTO
{
    
        private List<PqrsDTO> pqrs; 
            
        /**
	 * Constructor por defecto
	 */
	public PqrsDetailDTO( )
	{
            super( ); 
	}
        
         /**
         * Obtiene la lista de pqrs
         * @return Los pqrs
         */
         public List<PqrsDTO> getPqrs() 
         {
                return pqrs;
         }

        /**
         * Modifica la lista de pqrs
         * @param pPqrs Los pqrs a establecer
         */
         public void setPqrs(List<PqrsDTO> pPqrs) 
         {
                this.pqrs = pPqrs;
         }

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public PqrsDetailDTO( PqrsDetailDTO entity )
	{
		super( entity );

                if (entity != null) 
                {
                    pqrs = new ArrayList<>();
                    
                    for( PqrsDTO entityPqrs : entity.getPqrs() ) 
                    {
                        pqrs.add(new PqrsDTO(entityPqrs));
                    }
                }
        }

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public PqrsEntity toEntity( )
	{
		PqrsEntity entity = super.toEntity( );
                
                if( pqrs != null ) 
                {
                      List<PqrsEntity> pqrsEntity = new ArrayList<>();
                      
                      for( PqrsDTO dtoPqrs : pqrs ) 
                      {
                           pqrsEntity.add( dtoPqrs.toEntity() );
                      }
                      
                      entity.setPqrs(pqrsEntity);
                }
                
		return entity;
	}
        
       
    
}
