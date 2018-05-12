/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import java.io.Serializable;

/**
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 *
 * <pre>
 *   {
 *      "id":number,
 *      "categoria": String
 *   }
 * </pre>
 *
 * Por ejemplo una decoracion del catálogo se representa asi:<br>
 *
 * <pre>
 *
 *{
 *  "id":"45",
 *  "categoria":"Cumpleaños"
 * }
 *
 * </pre>
 *
 */

/**
 *
 * @author jf.garcia
 */
public class DecoracionCatalogoDTO implements Serializable {
    
    /**
     * Atributo que modela la categoria de la DecoracionCatalogo
     */
    private String categoria;
    
    /**
     * Atributo que modela el id de la DecoracionCatalogo
     */
    private Long id;
    
    /**
     * Constructor vacio
     */
    public DecoracionCatalogoDTO( )
    {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param decoracionCatalogoEntity: Es la entidad que se va a convertir a DTO
     */
    public DecoracionCatalogoDTO( DecoracionCatalogoEntity decoracionCatalogoEntity )
    {
        if (decoracionCatalogoEntity != null) 
        {
            this.categoria = decoracionCatalogoEntity.getCategoria();
            this.id = decoracionCatalogoEntity.getId();
        }
        
    }
    
    /**
     * Devuelve la cateogria.
     * @return categoria
     */
    public String getCategoria() {
        return categoria;
    }
    
    /**
     * Modifica la categoria.
     * @param categoria La nueva categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Devuelve el ID de la decoración del catálogo
     * @return el id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Modifica el ID de la decoración del catálogo
     * @param id El id nuevo.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public DecoracionCatalogoEntity toEntity(DecoracionCatalogoEntity entity )
    {
     
        entity.setCategoria( this.categoria );
        entity.setId( this.id );
        return entity;
    }
}
