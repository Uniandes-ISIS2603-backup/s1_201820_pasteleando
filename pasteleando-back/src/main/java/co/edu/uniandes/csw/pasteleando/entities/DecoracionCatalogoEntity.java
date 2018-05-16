/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * clase que representa una decoracion del catalogo y permite su serializacion
 * @author jf.garcia
 */
@Entity
public class DecoracionCatalogoEntity extends DecoracionEntity implements Serializable
{
    /**
     * Atributo que modela los pasteles de la DecoracionEntity
     */
    @PodamExclude
    @OneToMany(mappedBy = "decoracionCatalogo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PastelEntity> pasteles = new ArrayList<>();
    
    /**
     * Atributo que modela la categoria de la DecoracionCatalogoEntiry
     */
    private String categoria;
    
    /**
     * Atributo que modela las promociones asociadas de la DecoracionCatalogoEntity
     */
    @PodamExclude
    @OneToMany(mappedBy = "decoracionCatalogo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PromocionEntity> promociones = new ArrayList<>();
    
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
     * Devuelve las promociones de la decoración del catálogo.
     * @return Lista de entidades de tipo Promocion.
     */
    public List<PromocionEntity> getPromociones() {
        return promociones;
    }

    /**
     * Modifica las promociones de la decoración del catálogo.
     * @param promociones Las nuevas promociones.
     */
    public void setPromociones(List<PromocionEntity> promociones) {
        this.promociones = promociones;
    }

    /**
     * @return the pasteles
     */
    public List<PastelEntity> getPasteles() {
        return pasteles;
    }

    /**
     * @param pasteles the pasteles to set
     */
    public void setPasteles(List<PastelEntity> pasteles) {
        this.pasteles = pasteles;
    }

   
    
}
