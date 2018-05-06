/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;

/**
 * Clase que extiende de {@link PastelDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la entidad de Pastel vaya a la documentacion de {@link PastelDTO}
 *
 * 
 * @author MIGUELHOYOS
 */
public class PastelDetailDTO extends PastelDTO
{
    
    
    private DecoracionDTO decoracion;

    
      /**
         * constructor por defecto
         */
        
      public PastelDetailDTO()
      {    
          super();
      }
  
        /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param pastelEntity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
        public PastelDetailDTO(PastelEntity pastelEntity) 
        {
            super(pastelEntity);
            if (pastelEntity.getDecoracion() != null) 
            {
                this.decoracion = new DecoracionDTO(pastelEntity.getDecoracion());
            } 
            else 
            {
                pastelEntity.setDecoracion(null);
            }
        }
  
        /**
         * Obtiene la decoracion
         * @return La decoracion
         */
        public DecoracionDTO getDecoracion() {
            return decoracion;
        }

         /**
         * Define una decoracion
         * @return La decoracion
         */
        public void setDecoracion(DecoracionDTO decoracion) {
            this.decoracion = decoracion;
        }
        
   /**
     * transforma un DTO a un entity
     * @return la entidad construida a partir del DTO
     */
  public PastelEntity toEntity()
  {
      PastelEntity entity = super.toEntity();
       if (this.getDecoracion() != null) {
            entity.setDecoracion(this.getDecoracion().toEntity());
        }
      return entity;
  }
    
}

