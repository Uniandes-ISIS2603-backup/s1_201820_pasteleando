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
public class DecoracionPersonalizadaEntity extends BaseEntity implements Serializable
{
        
        private String color;
        
        private String imagen;
        
        private String estado;
        /**
	 * @return foto
	 */
	public String getColor( )
	{
		return color;
	}

	/**
	 * @param color the atribute to set
	 */
	public void setColor( String color )
	{
		this.color = color;
	}
        /**
	 * @return the imagen
	 */
	public String getImagen( )
	{
		return imagen;
	}

	/**
	 * @param imagen the atribute to set
	 */
	public void setImagen( String imagen )
	{
		this.imagen = imagen;
	}
        /**
	 * @return the Estado
	 */
	public String getEstado( )
	{
		return estado;
	}

	/**
	 * @param estado the atribute to set
	 */
	public void setEstado( String estado )
	{
		this.estado = estado;
	}
    
}
