/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    @OneToMany
    private DecoracionDTO decoracion;
    
  /**
     * constructor por defecto
     */
  //public PastelDetailDTO()
  //{    
  //}
  
  /**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param pastelEntity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
  public PastelDetailDTO(PastelEntity pastelEntity) {
        super(pastelEntity);
    }
  
   /**
     * transforma un DTO a un entity
     * @return la entidad construida a partir del DTO
     */
  public PastelEntity toEntity()
  {
      PastelEntity entity = super.toEntity();
      return entity;
  }
    
}
