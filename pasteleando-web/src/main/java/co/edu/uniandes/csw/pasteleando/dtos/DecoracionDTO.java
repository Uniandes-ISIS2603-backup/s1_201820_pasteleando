/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import java.util.Date;


/**
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * 
 * <pre>
 *   {
 *      "fechaAgregado": Date,
 *      "foto": String
 *   }
 * </pre>
 * 
 * Por ejemplo una decoracion se representa asi:<br>
 * 
 * <pre>
 * 
 *{
 *  "fechaAgregado":12/08/18,
 *  "foto": "C:\Users\dc.cepeda\Desktop\cake.jpg"
 * }
 * 
 * </pre>
 * 
 */

/**
 *
 * @author dc.cepeda
 */


public class DecoracionDTO {
    
        private Date fechaAgregado;
    
        private String foto;
        
      /**
	 * Constructor por defecto
	 */
	public DecoracionDTO( )
	{
	}
 /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param decoracionEntity: Es la entidad que se va a convertir a DTO
	 */
	public DecoracionDTO( DecoracionEntity decoracionEntity )
	{
		this.fechaAgregado = decoracionEntity.getFechaAgregado();
		this.foto=decoracionEntity.getFoto();
	}

        /**
	 * @return La fecha de la entidad Pasteleando
	 */
        
    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Date fecha) {
        this.fechaAgregado = fecha;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
	/**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public DecoracionEntity toEntity( )
	{
		DecoracionEntity entity = new DecoracionEntity( );
		entity.setFoto( this.foto );
		entity.setFechaAgregado( this.fechaAgregado );
		return entity;
	}
}
