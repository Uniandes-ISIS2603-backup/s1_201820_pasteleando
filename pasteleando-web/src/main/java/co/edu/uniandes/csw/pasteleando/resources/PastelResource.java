/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.PastelDetailDTO;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *<pre> clase que implementa elrecurso "psteles"
 * URL: /api/pasteles
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "pasteles".</i>
 * 
 * <h2> Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * 
 * @author MIGUELHOYOS
 */
@Path("pasteles")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PastelResource 
{    
      
    /**
     *  <h1>POST /api/pasteles : Crear un pastel.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PatelDetailDTO}.
     * 
     * Crea un nuevo pastel con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo pastel .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el pastel.
     * </code>
     * </pre>
     * @param pastel {@link PastelDetailDTO} - el pastel que se desea guardar
     * @return JSON {@link PastelDetailDTO}  - el pastel guardado con el atributo.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @Path("pasteles")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public void agregarPastel() throws BusinessLogicException
    {
        
    }
  
  /**
   *  <h1>GET /api/pasteles/{id} : Obtener pastel por id.</h1>
     * 
     * <pre>Busca el pastel con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el pastel correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una pastel con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del la pastel que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link PastelDetailDTO} - EL pastel buscado
     */

  @Path ("{id: \\\\d+}")
  @GET
  public PastelDetailDTO getPastel(@PathParam("id") Long id)
  {
      return null;
  }
  
      /**
     * <h1>GET /api/pasteles : Obtener todos los pasteles.</h1>
     * 
     * <pre>Busca y devuelve todos los pasteles que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los pasteles de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PastelDetailDTO} - Los pasteles encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PastelDetailDTO> getPasteles() {
        return new ArrayList<>();
    }
  /**
   * <h1>PUT /api/pasteles/{id} : Actualizar pastel con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PastelDetailDTO}.
     * 
     * Actualiza el pastel con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el pastel con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el pastel con el id dado.
     * </code> 
     * </pre>
     * @param <error>
     * @param id Identificador del pasteld que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param pastel {@link PastelDetaillDTO} el pastel que se desea guardar.
     * @retur JSON {@link PastelDetaillDTO} - El pastel que se desea actualizar.
     
     */
  @PUT
  @Path("{id: \\d+}")
  public PastelDetailDTO updatePastel(@PathParam("id") Long id, PastelDetailDTO city)
  {
      return null;
  }
  
  /**
     * <h1>DELETE /api/pasteles/{id} : Borrar pastel por id.</h1>
     * 
     * <pre>Borra el pastel con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el pastel correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un pastel con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del pastel que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
  public PastelDetailDTO deletePastel(@PathParam("id") Long id)
  {
      return null;
  }
}
