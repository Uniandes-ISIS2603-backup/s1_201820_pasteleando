/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author dc.cepeda
 */
@Entity
public class DecoracionEntity extends BaseEntity implements Serializable
{
        
        private String fechaAgregado;
        
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
	public String getFechaAgregado( )
	{
		return fechaAgregado;
	}

	/**
	 * @param fechaAgregado the atribute to set
	 */
	public void setFechaAgregado( String fechaAgregado )
	{
		this.fechaAgregado = fechaAgregado;
	}
}

