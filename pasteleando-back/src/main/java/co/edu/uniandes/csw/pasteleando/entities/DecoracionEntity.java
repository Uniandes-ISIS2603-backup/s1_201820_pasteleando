/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.entities;

import co.edu.uniandes.csw.pasteleando.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

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
    @OneToOne( fetch=FetchType.EAGER,  targetEntity = PastelEntity.class,cascade = CascadeType.PERSIST)
    private PastelEntity pastel; 
    
    /**
     * Atributo que modela la fecha de la DecoracionEntity
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaAgregado;
    /**
     * Atributo que modela la foto de la DecoracionEntity
     */
    private String foto;
     /**
     * Atributo que modela si es o no una decoracion personalizada de la DecoracionEntity
     */
    private Integer esPersonalizada;
    
    public DecoracionEntity()
    {
        
    }
 /**
     * @return esPersonalizada
     */
    public Integer getEsPersonalizada() {
        return esPersonalizada;
    }
/**
     * @param esPersonalizada the atribute to set
     */
    public void setEsPersonalizada(Integer esPersonalizada) {
        this.esPersonalizada = esPersonalizada;
    }
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

    public PastelEntity getPastel() {
        return pastel;
    }

    public void setPastel(PastelEntity pastel) {
        this.pastel = pastel;
    }
    
}