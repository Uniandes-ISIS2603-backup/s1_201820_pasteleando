/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jf.garcia
 */
public class PromocionDetailDTO extends PromocionDTO {
    
    /**
     * Atributo que modela la relación con el catálogo..
     */
    private DecoracionCatalogoDTO decoracionCatalogo;
    
    
    /**
     * constructor por defecto
     */
    
    public PromocionDetailDTO()
    {
        super();
    }
    
        /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param promocionEntity La entidad de promocioneando a partir de la cual se construye el objeto
	 */
        public PromocionDetailDTO(PromocionEntity promocionEntity) 
        {
            super(promocionEntity);
            
            if (promocionEntity != null) 
            {
                if (promocionEntity.getDecoracionCatalogo() != null) {
                    this.decoracionCatalogo = new DecoracionCatalogoDTO(promocionEntity.getDecoracionCatalogo());
                }
                else{
                promocionEntity.setDecoracionCatalogo(null);
                }
            }
        }
    
        /**
         * Obtiene la decoracion
         * @return La decoracion
         */
        public DecoracionCatalogoDTO getDecoracionCatalogo() {
            return decoracionCatalogo;
        }

         /**
         * Define una decoracion
         * @return La decoracion
         */
        public void setDecoracion(DecoracionCatalogoDTO decoracion) {
            this.decoracionCatalogo = decoracion;
        }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PromocionEntity toEntity( )
    {
        PromocionEntity entity = super.toEntity();
        if (getDecoracionCatalogo() != null) {
            entity.setDecoracionCatalogo(this.getDecoracionCatalogo().toEntity());
        }
        return entity;
    }
}
