/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import java.util.List;

/**
 *
 *Un ejemplo de la clase en JSON es:
 * <pre>
 * { 
 * "numeropuntos":200
 * }
 * </pre>
 * 
 * @author m.leona
 */
public class TarjetaPuntosDetailDTO extends TarjetaPuntosDTO{
    
    List<FacturaDTO> facturas; 
    
    
    
    /**
     * Constructor por defecto
     */
    public TarjetaPuntosDetailDTO()
    {
        super();
    }
}
