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
public class PromocionEntity extends BaseEntity implements Serializable{
    
    /**
     * Atributo que modela el porcentaje de la promocionEntity
     */
    public Integer cantidad;
        
        /**
	 * @return cantidad
	 */
	public int getCantidad( )
	{
		return cantidad;
	}

	/**
	 * @param cantidad the atribute to set
	 */
	public void setCantidad( int cantidad )
	{
		this.cantidad = cantidad;
	}
    
}
