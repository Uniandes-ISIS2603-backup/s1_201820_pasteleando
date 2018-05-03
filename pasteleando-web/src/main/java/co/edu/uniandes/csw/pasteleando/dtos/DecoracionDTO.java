/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import java.io.Serializable;
import java.util.Date;


/**
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * 
 * <pre>
 *   {
 *      "fechaAgregado": Date,
 *      "foto": String, 
 *      "esPersonalizada": Boolean
 *   }
 * </pre>
 * </br>
 * Por ejemplo una decoracion se representa asi:<br>
 * 
 * <pre>
 * 
 *{
 *  "fechaAgregado":12/08/18,
 *  "foto": "C:\Users\dc.cepeda\Desktop\cake.jpg"
 * "esPersonalizada": true
 * }
 * </br>
 * </pre>
 * 
 */

/**
 *
 * @author dc.cepeda
 */


public class DecoracionDTO implements Serializable {
 
     /**
     * Atributo que modela la fecha de la Decoracion
     */
        private Date fechaAgregado;
    
        /**
     * Atributo que modela la foto de la Decoracion
     */
        private String foto;
           /**
     * Atributo que modela si la decoracion es Personalizada
     */
        private int esPersonalizada;
        
        private Long id;
       
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
            if(decoracionEntity!=null)
            {
		this.fechaAgregado = decoracionEntity.getFechaAgregado();
		this.foto=decoracionEntity.getFoto();
                this.esPersonalizada=decoracionEntity.getEsPersonalizada();
                this.id=decoracionEntity.getId();
            }
	}
/**
            * @return Si la entidad es Personalizada
	 */
    public int getEsPersonalizada() {
        return esPersonalizada;
    }
/**
	 * @param esPersonalizada El nuevo estado de la entidad Decoracion
	 */
    public void setEsPersonalizada(int esPersonalizada) {
        this.esPersonalizada = esPersonalizada;
    }

        /**
	 * @return La fecha de la entidad Decoracion
	 */
        
    public Date getFechaAgregado() {
        return fechaAgregado;
    }
        /**
	 * @param fecha La nueva fecha de la entidad Decoracion
	 */
    public void setFechaAgregado(Date fecha) {
        this.fechaAgregado = fecha;
    }
         /**
	 * @return La foto de la entidad Decoracion
	 */
    public String getFoto() {
        return foto;
    }
        /**
	 * @param foto La nueva foto de la entidad decoracion
	 */
    public void setFoto(String foto) {
        this.foto = foto;
    }
     public Long getId() {
        return id;
    }
        /**
	 * @param id El nuevo id de la entidad decoracion
	 */
    public void setId(Long id) {
        this.id = id;
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
                entity.setEsPersonalizada(this.esPersonalizada);
                entity.setId(this.getId());
		return entity;
	}
}
