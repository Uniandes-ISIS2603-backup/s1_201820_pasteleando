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
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * clase que representa una promocion en la base de datos y permite su serializacion
 * @author jf.garcia
 */
@Entity
public class PromocionEntity extends BaseEntity implements Serializable{
    
    /**
     * Atributo que modela el porcentaje de la promocionEntity
     */
    private Integer cantidad;
    
    /**
     * Atributo que modela la decoración del catálogo asociada.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DecoracionCatalogoEntity decoracionCatalogo;
    
    
   
    
    /**
     * @return cantidad
     */
    public Integer getCantidad( )
    {
        return cantidad;
    }
    
    /**
     * @param cantidad the atribute to set
     */
    public void setCantidad( Integer cantidad )
    {
        this.cantidad = cantidad;
    }
    
    /**
     * Devuelve la decoración del catálogo asociada a esta promoción.
     * @return Entidad de tipo decoracionCatalogo
     */
    public DecoracionCatalogoEntity getDecoracionCatalogo() {
        return decoracionCatalogo;
    }
    
    /**
     * Modifica la decoración del catálogo asociada a esta promoción.
     * @param decoracionCatalogo El nuevo decoracionCatalogo
     */
    public void setDecoracionCatalogo(DecoracionCatalogoEntity decoracionCatalogo) {
        this.decoracionCatalogo = decoracionCatalogo;
    }
    
}
