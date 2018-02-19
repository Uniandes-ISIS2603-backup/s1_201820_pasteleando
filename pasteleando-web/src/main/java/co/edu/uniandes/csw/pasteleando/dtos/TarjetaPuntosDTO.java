/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

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
    *@return Numero de puntos de la tarjeta
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
    
}
