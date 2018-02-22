/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.dtos;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;

/**
 * Clase que extiende de {@link DecoracionCatalogoDTO} para manejar las relaciones entre
 * las DecoracionCatalogoDTO JSON y otros DTOs. Para conocer el
 * contenido de la clase vaya a la documentacion de {@link DecoracionCatalogoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "categoria": string,
 *      "promocion": {
 *      "cantidad": int,
 *      }
 *   }
 * </pre>
 * Por ejemplo una decoración catálogo se representa asi:<br>
 *
 * <pre>
 *   {
 *      "categoria": "Bodas",
 *      "promocion": {
 *      "cantidad": 20,
 *      }
 *   }
 * </pre>
 * @author jf.garcia
 */
public class DecoracionCatalogoDetailDTO extends DecoracionCatalogoDTO
{
    /**
     * Relación a una promoción
     */
    private PromocionDTO promocion;
    
    /**
     * Constructor por defecto
     */
    public DecoracionCatalogoDetailDTO( )
    {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
     */
    public DecoracionCatalogoDetailDTO( DecoracionCatalogoEntity entity )
    {
        super( entity );
        if (entity.getPromocion() != null) {
            this.promocion = new PromocionDTO(entity.getPromocion());
        } else {
            entity.setPromocion(null);
        }
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public DecoracionCatalogoEntity toEntity( )
    {
        DecoracionCatalogoEntity decoracionCatalogoEntity = super.toEntity( );
        if (this.getPromocion() != null) {
            decoracionCatalogoEntity.setPromocion(this.getPromocion().toEntity());
        }
        return decoracionCatalogoEntity;
    }
    
    /**
     * Modifica la promocion asociada a esta decoración catálogo.
     * @param promocion the promocion to set
     */
    public void setPromocion(PromocionDTO promocion) {
        this.promocion = promocion;
    }
    
    /**
     * Devuelve la promocion asociada a esta decoracion catálogo.
     * @return DTO de promocion
     */
    public PromocionDTO getPromocion() {
        return promocion;
    }
    
}

