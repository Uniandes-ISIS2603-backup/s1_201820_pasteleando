/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;


/**
 * *Un ejemplo de este DTO es:
 * <br>
*<pre>
*{
 *"id":1907, 
 *"direccion":"Calle 127 #47-48",
 *"fecha":"13/02/18", 
 *"hora":"22:00",
 *"precio":"55,000 COP",
 *"TarjetaPuntos":[{
 * "NumeroPuntos: 0"
 * }]
*}
*</pre>
* 
 * @author m.leona
 */
public class FacturaDetailDTO extends FacturaDTO {
  
        TarjetaPuntosDTO tarjeta;
        
	/**
	 * Constructor por defecto
	 */
	public FacturaDetailDTO( )
	{
            super();
	}

}
