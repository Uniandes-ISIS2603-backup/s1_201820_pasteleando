/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dc.cepeda
 */
@Entity
public class DecoracionEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToMany
    private PastelEntity pastel;
     /**
     * Atributo que modela la fecha de la DecoracionEntiry
     */    
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date fechaAgregado;
     /**
     * Atributo que modela la foto de la DecoracionEntity
     */
        private String foto;
        
        /**
	 * @return foto
	 */
	public String getFoto( )
	{
		return foto;
	}

	/**
	 * @param foto the atribute to set
	 */
	public void setFoto( String foto )
	{
		this.foto = foto;
	}
        /**
	 * @return the fechaAgregado
	 */
	public Date getFechaAgregado( )
	{
		return fechaAgregado;
	}

	/**
	 * @param fechaAgregado the atribute to set
	 */
	public void setFechaAgregado( Date fechaAgregado )
	{
		this.fechaAgregado = fechaAgregado;
	}
}

