/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;

/**
 *Un ejemplo de la clase en JSON es:
 * <pre>
 * { 
 * "numeropuntos":200
 * }
 * </pre>
 * 
 * @author m.leona
 */
public class TarjetaPuntosDTO {
    //Atributos
    //Id al que se hace referencia
    /**
     * Referencia a la base de datos
     */
    private Long id;
    
    //Numero de puntos que tiene la tarjeta
    
    /**
     * Numero de puntos que tiene la tarjeta
     */
    private Integer numeroPuntos;
    
  
    
    /**
     * Constructor por defecto
     */
    public TarjetaPuntosDTO()
    {
     /**
      * Vacío
      */
    }
    
     /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param TarjetaPuntosEntity: Es la entidad que se va a convertir a DTO
	 */
	public TarjetaPuntosDTO( TarjetaPuntosEntity tarjetaPuntosEntity )
	{
		this.numeroPuntos = tarjetaPuntosEntity.getNumeroPuntos();
		this.id=tarjetaPuntosEntity.getId();
	}
    
    //metodos
    
    /**
    @return el id que esta asoiciado
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * @param pId Id nuevo
    */
    public void setId(Long pId)
    {
        this.id = pId;
    }
    
    /**
    *@return numeroPuntos Numero de puntos de la tarjeta
    */
    public Integer getNumeroPuntos()
    {
        return numeroPuntos;
    }
    /**
    *@param pPuntos nuevos puntos de la tarjeta
    */
    public void setNumeroPuntos(Integer pPuntos)
    {
        this.numeroPuntos=pPuntos;
    }
    
    /**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public TarjetaPuntosEntity toEntity( )
	{
		TarjetaPuntosEntity entity = new TarjetaPuntosEntity( );
		entity.setNumeroPuntos(this.numeroPuntos);
		entity.setId( this.id );
		return entity;
	}
    
}
