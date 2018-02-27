/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    /**
     * Atributo que modela los pasteles de la DecoracionEntity
     */    
    @PodamExclude
    @OneToMany(mappedBy = "Decoracion")
    private List<PastelEntity> pasteles = new ArrayList<>();

     /**
     * Atributo que modela la fecha de la DecoracionEntity
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
        
    public List<PastelEntity> getPasteles() {
        return pasteles;
    }

    public void setPasteles(List<PastelEntity> pasteles) {
        this.pasteles = pasteles;
    }
       
}

