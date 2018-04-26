/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.ArrayList;
import java.util.List;

/**
 ** *Un ejemplo de este DTO es:
 * <br>
*<pre>
*{
 *"color":"Azul",
 *  "imagen": "C:\Users\dc.cepeda\Desktop\cake.jpg",
 *  "estado": "aprobado",
 *"Decoracion":[{
 * "esPersonalizada: true
 * }]
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDetailDTO extends DecoracionPersonalizadaDTO
{
        ArrayList<PastelDTO> pasteles;
    
	/**
	 * Constructor por defecto
	 */
	public DecoracionPersonalizadaDetailDTO( )
	{
	}

	/**
	 * Constructor para transformar un Entity a un DTO
	 *
	 * @param entity La entidad de Pasteleando a partir de la cual se construye el objeto
	 */
	public DecoracionPersonalizadaDetailDTO( DecoracionPersonalizadaEntity entity )
	{
		super( entity );
        if(entity.getPasteles() != null)
        {
           this.pasteles = new ArrayList<PastelDTO>();
           for(int i = 0; i < entity.getPasteles().size(); i++)
           {
                PastelDTO nuevo = new PastelDTO( entity.getPasteles().get(i)); 
                this.pasteles.add(nuevo);
           }          
        }
        else
        {
            this.pasteles = null;
            
        }
	}

	/**
	 * Transformar un DTO a un Entity
	 *
	 * @return La entidad construida a partir del DTO.
	 */
	public DecoracionPersonalizadaEntity toEntity( )
	{
		DecoracionPersonalizadaEntity entity = super.toEntity();
        
        if(this.pasteles != null)
        {
           entity.setPasteles(new ArrayList<PastelEntity>());
           for(int i = 0; i < this.pasteles.size(); i++)
           {
               PastelEntity nuevo = pasteles.get(i).toEntity();
               entity.getPasteles().add(nuevo);
           }          
        }
        return entity;
	}

    public ArrayList<PastelDTO> getPasteles() {
        return pasteles;
    }

    public void setPasteles(ArrayList<PastelDTO> pasteles) {
        this.pasteles = pasteles;
    }

}
