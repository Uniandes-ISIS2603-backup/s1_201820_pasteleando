/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *Un ejemplo de la clase en JSON es:
 * <pre>
 * { 
 * "numeropuntos":200
 * "Facturas":[
 * {
 * "id":1907, 
 * "direccion":"Calle 127 #47-48",
 * "fecha":"13/02/18", 
 * "hora":"22:00",
 * "precio":"55,000 COP"
 * },
 * {
 * "id":2020, 
 *"direccion":"Calle 100 #19A-08",
 *"fecha":"23/06/18", 
 *"hora":"10:00",
 *"precio":"47,000 COP"
 * }]
 * }
 * </pre>
 * 
 * @author m.leona
 */
public class TarjetaPuntosDetailDTO extends TarjetaPuntosDTO{
    
    private ClienteDTO cliente;
   
   private List<FacturaDTO> facturas; 

    public ClienteDTO getCliente() {
        return cliente;
    }

    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }
    
    
    
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
        
        if(entity.getFacturas() != null)
        {
           facturas = new ArrayList<>();
            for (FacturaEntity entityFactura : entity.getFacturas()) {
                    facturas.add(new FacturaDTO(entityFactura));
                }
       }
    }
}
