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
 * @author jf.garcia
 */
@Entity
public class DecoracionCatalogoEntity extends BaseEntity implements Serializable
{
     /**
     * Atributo que modela la fecha de la DecoracionEntiry
     */  
        public String categoria;
        
        /**
	 * @return categoria
	 */
	public String getCategoria( )
	{
		return categoria;
	}

	/**
	 * @param categoria the atribute to set
	 */
	public void setCategoria( String categoria )
	{
		this.categoria = categoria;
	}
    
}
