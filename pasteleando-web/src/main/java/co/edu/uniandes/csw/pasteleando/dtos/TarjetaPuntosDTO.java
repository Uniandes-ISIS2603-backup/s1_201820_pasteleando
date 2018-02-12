/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

/**
 *
 * @author m.leona
 */
public class TarjetaPuntosDTO {
    //Atributos
    //Id al que se hace referencia
    private Long id;
    
    //Numero de puntos que tiene la tarjeta
    private int numeroPuntos;
    
    //Indica si se puede redimir un descuento
    private boolean esRedimible;
    
    //constructor
    
    /*
    @param pNumeroPuntos Indica el numero de puntos de la tarjeta
    @param pEsRedimible Indica si la tarjeta es redimible para un descuento
    */
    public TarjetaPuntosDTO(int pNumeroPuntos, boolean pEsRedimible)
    {
        numeroPuntos = pNumeroPuntos;
        esRedimible = pEsRedimible;
    }
    
    //metodos
    
    /*
    @return el id que esta asoiciado
    */
    public Long getId()
    {
        return id;
    }
    
    /*
    @param pId Id nuevo
    */
    public void setId(Long pId)
    {
        this.id = pId;
    }
    
    /*
    @return Numero de puntos de la tarjeta
    */
    public int getNumeroPuntos()
    {
        return numeroPuntos;
    }
    /*
    @param pPuntos nuevos puntos de la tarjeta
    */
    public void setNumeroPuntos(int pPuntos)
    {
        this.numeroPuntos=pPuntos;
    }
    
    /*
    @return Si la tarjeta es redimible para un descuento o no
    */
    public boolean getEsRedimible()
    {
        return esRedimible;
    }
    
    /*
    @Param pEsRedimible Nuevo estado de la tarjeta
    */
    public void setEsRedimible(boolean pEsRedimible)
    {
        this.esRedimible=pEsRedimible;
    }
}
