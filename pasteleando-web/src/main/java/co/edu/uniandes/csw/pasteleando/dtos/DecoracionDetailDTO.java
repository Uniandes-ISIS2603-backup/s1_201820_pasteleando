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
     * Atributo que modela la coleccion de pasteles 
     */
        private PastelDTO pastel;

	/**
	 * Constructor por defecto
	 */
	public DecoracionDetailDTO( )
	{
            super();
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public DecoracionDetailDTO( DecoracionEntity entity )
	{
		super( entity );
                 if (entity != null)
                 {
                     this.pastel=new PastelDTO(entity.getPastel());
                 }
	}
        /**
         
	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	@Override
	public DecoracionEntity toEntity( )
	{
		DecoracionEntity decoracionEntity = super.toEntity( );
                if (pastel != null) {
                decoracionEntity.setPastel(pastel.toEntity());
        }
		return decoracionEntity;
	}

        /**
     * @return pastel
     */
    public PastelDTO getPastel() {
        return pastel;
    }
 /**
     * @param pastel the atribute to set
     */
    public void setPastel(PastelDTO pastel) {
        this.pastel = pastel;
    }
        

}