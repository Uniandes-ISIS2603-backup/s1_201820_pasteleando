/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import java.util.ArrayList;
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
    
    private ClienteDTO cliente;
   
   private List<FacturaDTO> facturas; 
    
    
    
    /**
     * Constructor por defecto
     */
    public TarjetaPuntosDetailDTO()
    {
        super();
    }
    
    
    public TarjetaPuntosDetailDTO(TarjetaPuntosEntity entity)
    {
        super(entity);
        if(entity.getCliente() != null)
        {
            this.cliente = new ClienteDTO(entity.getCliente());
        }
        else
        {
            entity.setCliente(null);
        }
        
       // if(entity.getFacturas() != null)
        //{
          //  facturas = new ArrayList<>();
           // for()
        //}
    }
}
