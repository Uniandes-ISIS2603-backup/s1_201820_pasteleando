/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;

/**
 *
 * @author jf.garcia
 */
public class PromocionDTO {
      private int cantidad;
        
    /**
	 * Constructor por defecto
	 */
	public PromocionDTO( )
	{
	}

	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param PromocionEntity: Es la entidad que se va a convertir a DTO
	 */
	public PromocionDTO( PromocionEntity PromocionEntity )
	{
		this.cantidad = PromocionEntity.getCantidad();

	}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public PromocionEntity toEntity( )
	{
		PromocionEntity entity = new PromocionEntity( );
		entity.setCantidad( this.cantidad );
		return entity;
	}
}
