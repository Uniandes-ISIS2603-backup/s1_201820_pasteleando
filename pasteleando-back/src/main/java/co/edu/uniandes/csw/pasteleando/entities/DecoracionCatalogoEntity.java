/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jf.garcia
 */
@Entity
public class DecoracionCatalogoEntity extends BaseEntity implements Serializable
{
    /**
     * Atributo que modela la categoria de la DecoracionCatalogoEntiry
     */
    public String categoria;
    
    /**
     * Atributo que modela la promoción de la DecoracionCatalogoEntity
     */
    @OneToMany
    public PromocionEntity promocion;
    
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
    
    /**
     * Devuelve la promocion a la que pertenece la decoración catálogo.
     * @return Una entidad de promocion.
     */
    public PromocionEntity getPromocion() {
        return promocion;
    }
    
    /**
     * Modifica la promocion a la que pertenece la decoración catálogo.
     * @param promocion La nueva promocion.
     */
    public void setPromocion(PromocionEntity promocion) {
        this.promocion = promocion;
    }
    
}
