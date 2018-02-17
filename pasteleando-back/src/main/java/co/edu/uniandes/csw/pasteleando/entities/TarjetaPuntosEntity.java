/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

/**
 *
 * @author m.leona
 */
public class TarjetaPuntosEntity {
    
    private Long id;
    
    private Integer numeroPuntos;
    
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
