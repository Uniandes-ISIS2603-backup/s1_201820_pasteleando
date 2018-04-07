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
public class DecoracionPersonalizadaEntity extends DecoracionEntity implements Serializable
{
    /**
     * Atributo que modela el color de la DecoracionPersonalizada
     */
    private String color;
    /**
     * Atributo que modela la imagen de la DecoracionPersonalizada
     */
    private String imagen;
    /**
     * Atributo que modela el estado de la DecoracionPersonalizada
     */
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
