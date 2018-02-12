/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PasteleandoEntity;

/**
 *
 * @author m.leona
 */
public class FacturaDTO {
    
    //Atributos
    
    //id en la base de datos
    private Long id;
    
    //Dirección a la cual se hace el comprobante de pago
    private String direccion;
    
    //Fecha en que se facturó
    private String fecha;
    
    //Hora del comprobante de pago
    private int hora;
    
    //Precio total de la factura
    private int precio;
    
    //Constructor
    
    /*
    @param pDireccion Dirección asociada a la factura
    @param pFecha Fecha asociada a la factura
    @param pHora Hora de expedición de la factura
    @param pPrecio Precio total de la factura
    */
    public FacturaDTO(String pDireccion, String pFecha, int pHora, int pPrecio)
    {
        direccion = pDireccion;
        fecha = pFecha;
        hora = pHora;
        precio = pPrecio;
    }
    
    //Metodos
    
    /*
    @return id al que está asociado
    */
    public Long getId()
    {
        return id;
    }
    /*
    @return Dirección de la factura
    */
    public String getDireccion()
    {
        return direccion;
    }
    
    /*
    @param pDireccion Nueva direccion de la factura
    */
    public void setDireccion(String pDireccion)
    {
        this.direccion=pDireccion;
    }
    
    /*
    @return fecha de la factura
    */
    public String getFecha()
    {
        return fecha;
    }
    
   
    /*
    @param Nuevo id
    */
    public void setId(Long id)
    {
     this.id = id;   
    }
    
    /*
    @param pFecha Nueva fecha
    */
    public void setFecha(String pFecha)
    {
        this.fecha=pFecha;
    }
    
    /*
    @return Hora de la factura
    */
    public int getHora()
    {
        return hora;
    }
    
    /*
    @param pHora Nueva hora
    */
    public void setHora(int pHora)
    {
        this.hora=pHora;
    }
    
    /*
    @return precio total de la compra
    */
    public int getPrecio()
    {
        return precio;
    }
    
    public void setPrecio(int pPrecio)
    {
        this.precio = pPrecio;
    }
}
