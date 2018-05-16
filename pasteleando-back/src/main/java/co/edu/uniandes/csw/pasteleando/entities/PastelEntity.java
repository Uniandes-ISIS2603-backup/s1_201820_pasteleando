/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import uk.co.jemos.podam.common.PodamExclude;

/**
 *clase que representa un pastel en la base de datos y permite su serializacion
 * @author MIGUELHOYOS
 */
@Entity
public class PastelEntity extends BaseEntity implements Serializable
{
    private Integer peso;
    private double precio;
    
    @PodamExclude
    @ManyToOne
    private DecoracionCatalogoEntity decoracionCatalogo;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DecoracionPersonalizadaEntity decoracionPersonalizada;
    
    /**
     * retorna el peso del pastel
     * @return peso
     */
    public Integer getPeso()
    {
        return peso;
    }
    
    /**
     * actualiza el peso con el peso que llega por parametro
     * @param pPeso
     */
    public void setPeso(Integer pPeso)
    {
        this.peso = pPeso;
    }
    
    /**
     * retorna el precio del pastel
     * @return
     */
    public double getPrecio()
    {
        return precio;
    }
    
    /**
     * actualiza el precio con el precio que llega por parametro
     * @param pPrecio
     */
    public void setPrecio(double pPrecio)
    {
        this.precio = pPrecio;
    }

    /**
     * retorna la decoracion asociada a el pastel
     * @return the decoracion
     */
    public DecoracionCatalogoEntity getDecoracionCatalogo() {
        return decoracionCatalogo;
    }

    /**
     * establece la decoracion asociada al pastel
     * @param decoracion the decoracion to set
     */
    public void setDecoracion(DecoracionCatalogoEntity decoracion) {
        this.decoracionCatalogo = decoracion;
    }

    /**
     * retorna la decoracion asociada al pastel
     * @return the decoracionPersonalizada
     */
    public DecoracionPersonalizadaEntity getDecoracionPersonalizada() {
        return decoracionPersonalizada;
    }

    /**
     * establece la decoracion asociada al pastel
     * @param decoracionPersonalizada the decoracionPersonalizada to set
     */
    public void setDecoracionPersonalizada(DecoracionPersonalizadaEntity decoracionPersonalizada) {
        this.decoracionPersonalizada = decoracionPersonalizada;
    }
    
   
}