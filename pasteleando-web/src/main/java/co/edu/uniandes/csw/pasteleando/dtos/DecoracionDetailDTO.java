/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dc.cepeda
 */

public class DecoracionDetailDTO extends DecoracionDTO
{
         /**
     * Atributo que modela la coleccion de pasteles 
     */
        private List <PastelDTO> pasteles;
        
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
                 if (entity != null) {
            if (entity.getPasteles() != null) {
                pasteles = new ArrayList<>();
                for (PastelEntity entityPastel : entity.getPasteles()) {
                    pasteles.add(new PastelDTO(entityPastel));
                }
            }
        }
	}

        /**
           * retorna la lista del pastel
           * @return pastel Lista del Pastel
       */
        public List<PastelDTO> getPasteles() {
            return pasteles;
        }
         /**
           * actualiza la lista de pastel con la que llega por parametro
           * @param pasteles Nueva lista de pastel
           */
        public void setPasteles(List<PastelDTO> pasteles) {
            this.pasteles = pasteles;
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
                if (pasteles != null) {
            List<PastelEntity> pastelEntity = new ArrayList<>();
            for (PastelDTO dtoPastel : pasteles) {
                pastelEntity.add(dtoPastel.toEntity());
            }
            decoracionEntity.setPasteles(pastelEntity);
        }
		return decoracionEntity;
	}

}
