/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.DecoracionPersonalizadaDTO;
import co.edu.uniandes.csw.pasteleando.dtos.DecoracionPersonalizadaDetailDTO;
import co.edu.uniandes.csw.pasteleando.ejb.DecoracionPersonalizadaLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.mappers.BusinessLogicExceptionMapper;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "Decoración Personalizada".
 * URL: /api/Decoración Personalizada
 * </pre>
 *
 * @author dc.cepeda
 */
@Path( "personalizada" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class DecoracionPersonalizadaResource
{
    @Inject
    private DecoracionPersonalizadaLogic decoracionPersonalizadaLogic;
    
    /**
     * <h1>GET /api/personalizada : Obtener todas las entidades de decoración del personalizada.</h1>
     * <pre>Busca y devuelve todas las entidades de decoración del personalizada que existen en la aplicación.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las entidades de decoración personalizada de la aplicación.</code>
     * </pre>
     *
     * @return JSONArray {@link DecoracionPersonalizadaDetailDTO} - Las entidades de decoración personalizada encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<DecoracionPersonalizadaDetailDTO> getDecoracionesPersonalizadas() {
        return listDecoracionPersonalizadaEntity2DetailDTO(decoracionPersonalizadaLogic.getDecoracionesPersonalizadas());
    }
    
    /**
     * <h1>GET /api/personalizada/{id} : Obtener una entidad de Decoración Personalizada por id.</h1>
     * <pre>Busca la entidad de decoración del personalizada con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la entidad de decoración del personalizada correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una entidad de decoración del personalizada con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de decoración del personalizada que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link DecoracionPersonalizadaDetailDTO} - La entidad de decoración personalizada buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public DecoracionPersonalizadaDetailDTO getDecoracionPersonalizada(@PathParam("id") Long id) {
        DecoracionPersonalizadaEntity entity = decoracionPersonalizadaLogic.getDecoracionPersonalizada(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /personalizada/" + id + " no existe.", 404);
        }
        return new DecoracionPersonalizadaDetailDTO(entity);
    }
    
    /**
     * <h1>POST /api/personalizada : Crear una entidad de Decoración Personalizada.</h1>
     * <pre>Cuerpo de petición: JSON {@link DecoracionPersonalizadaDetailDTO}.
     *
     * Crea una nueva entidad de decoración personalizada con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Decoración Personalizada.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Decoración Personalizada.
     * </code>
     * </pre>
     *
     * @param decoracionPersonalizada {@link DecoracionPersonalizadaDetailDTO} - La entidad de la decoración personalizada que se desea guardar.
     * @return JSON {@link DecoracionPersonalizadaDetailDTO}  - La entidad de decoración personalizada guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de decoración personalizada.
     */
    @POST
    public DecoracionPersonalizadaDetailDTO createDecoracionPersonalizada(DecoracionPersonalizadaDetailDTO decoracionPersonalizada) throws BusinessLogicException {
          return new DecoracionPersonalizadaDetailDTO(decoracionPersonalizadaLogic.createDecoracionPersonalizada(decoracionPersonalizada.toEntity()));
    }
    
    /**
     * <h1>PUT /api/personalizada/{id} : Actualizar una entidad de Decoración Personalizada con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link DecoracionPersonalizadaDetailDTO}.
     *
     * Actualiza la entidad de Decoración Personalizada con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la entidad de Decoración Personalizada con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Decoración Personalizada con el id dado.
     * </code>
     * </pre>
     *
     * @param id        Identificador de la entidad de Decoración Personalizada que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param decoracionPersonalizada {@link DecoracionPersonalizadaDetailDTO} La entidad de Decoración Personalizada que se desea guardar.
     * @return JSON {@link DecoracionPersonalizadaDetailDTO} - La entidad de Decoración Personalizada guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Decoración Personalizada porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public DecoracionPersonalizadaDetailDTO updateDecoracionPersonalizada(@PathParam("id") Long id, DecoracionPersonalizadaDetailDTO decoracionPersonalizada) throws WebApplicationException, BusinessLogicException {
        decoracionPersonalizada.setId(id);
        DecoracionPersonalizadaEntity entity = decoracionPersonalizadaLogic.getDecoracionPersonalizada(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /personalizada/" + id + " no existe.", 404);
        }
        return new DecoracionPersonalizadaDetailDTO(decoracionPersonalizadaLogic.updateDecoracionPersonalizada(id, decoracionPersonalizada.toEntity()));
    }
    
    /**
     * <h1>DELETE /api/personalizada/{id} : Borrar una entidad de Decoración Personalizada por id.</h1>
     * <pre>Borra la entidad de Decoración Personalizada con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la entidad de Decoración Personalizada correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Decoración Personalizada con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de Decoración Personalizada que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la decoración del personalizada.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDecoracionPersonalizada(@PathParam("id") Long id) throws BusinessLogicException {
        DecoracionPersonalizadaEntity entity = decoracionPersonalizadaLogic.getDecoracionPersonalizada(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso //" + id + " no existe.", 404);
        }
        decoracionPersonalizadaLogic.deleteDecoracionPersonalizada(id);
    }
    private List<DecoracionPersonalizadaDetailDTO> listDecoracionPersonalizadaEntity2DetailDTO(List<DecoracionPersonalizadaEntity> entityList) {
        List<DecoracionPersonalizadaDetailDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new DecoracionPersonalizadaDetailDTO(entity));
        });
        return list;
    }
}
