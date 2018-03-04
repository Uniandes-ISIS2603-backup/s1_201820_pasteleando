
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.PromocionDTO;
import co.edu.uniandes.csw.pasteleando.ejb.PromocionLogic;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
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
 * <pre>Clase que implementa el recurso "promoción".
 * URL: /api/Promocion
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "promocion".</i>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path( "promocion" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class PromocionResource
{
    
    @Inject
            PromocionLogic promocionLogic;
    
    /**
     * <h1>GET /api/promocion : Obtener todas las entidades de promoción.</h1>
     * <pre>Busca y devuelve todas las entidades de promoción que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las entidades de promoción de la aplicacion.</code>
     * </pre>
     * @param catalogoId El ID del catálogo del cual se buscan las promociones.
     * @return JSONArray {@link PromocionDTO} - Las promociones encontradas en la decoración del catálogo. Si no hay ninguna retorna una lista vacía.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el catálogo.
     */
    @GET
    public List<PromocionDTO> getPromociones(@PathParam("catalogoId") Long catalogoId) throws BusinessLogicException {
        return listEntity2DTO(promocionLogic.getPromociones(catalogoId));
    }
    
    /**
     * <h1>GET /api/promocion/{id} : Obtener una entidad de promoción por id.</h1>
     *
     * <pre>Busca la entidad de promoción con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la entidad de promoción correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una entidad de promoción con el id dado.
     * </code>
     * </pre>
     * @param catalogoId El ID del catálogo del cual se buscan las promociones.
     * @param id El ID de la promoción que se busca
     * @return {@link ReviewDTO} - La promoción encontrada en el catálogo.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se encuentra el catálogo.
     */
    @GET
    @Path("{id: \\d+}")
    public PromocionDTO getPromocion(@PathParam("catalogoId") Long catalogoId, @PathParam("id") Long id) throws BusinessLogicException {
        PromocionEntity entity = promocionLogic.getPromocion(catalogoId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + "/promocion/" + id + " no existe.", 404);
        }
        return new PromocionDTO(entity);
    }
    
    /**
     * <h1>POST /api/promocion : Crear una entidad de promociónn.</h1>
     * <pre>Cuerpo de petición: JSON {@link PromocionDTO}.
     *
     * Crea una nueva entidad de promoción con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de promocion.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de promoción.
     * </code>
     * </pre>
     * @param catalogoId El ID del catálogo del cual se guarda la promoción.
     * @param promocion {@link PromocionDTO} - La entidad de promoción que se desea guardar.
     * @return JSON {@link PromocionDTO}  - La entidad de promoción guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de promoción.
     */
    @POST
    public PromocionDTO createReview(@PathParam("catalogoId") Long catalogoId, PromocionDTO promocion) throws BusinessLogicException {
        return new PromocionDTO(promocionLogic.createPromocion(catalogoId, promocion.toEntity()));
    }
    
    /**
     * <h1>PUT /api/promocion/{id} : Actualizar una entidad de promoción con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PromocionDTO}.
     *
     * Actualiza la entidad de promoción con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la entidad de promoción con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de promoción con el id dado.
     * </code>
     * </pre>
     * @param catalogoId El ID del catálogo del cual se guarda la promoción
     * @param id        Identificador de la entidad de promoción que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param promocion {@link PromocionDTO} La entidad de promoción que se desea guardar.
     * @return JSON {@link PromocionDTO} - La entidad de promoción guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de promoción porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PromocionDTO updatePromocion(@PathParam("catalogoId") Long catalogoId, @PathParam("id") Long id, PromocionDTO promocion) throws BusinessLogicException {
        promocion.setId(id);
        PromocionEntity entity = promocionLogic.getPromocion(catalogoId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + "/promocion/" + id + " no existe.", 404);
        }
        return new PromocionDTO(promocionLogic.updatePromocion(catalogoId, promocion.toEntity()));
        
    }
    
    /**
     * <h1>DELETE /api/promocion/{id} : Borrar una entidad de promoción por id.</h1>
     *
     * <pre>Borra la entidad de promoción con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la entidad de promoción correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de promoción con el id dado.
     * </code>
     * </pre>
     *
     * @param catalogoId El ID del catálogo del cual se va a eliminar la promoción.
     * @param id El ID de la promoción que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la promoción.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("catalogoId") Long catalogoId, @PathParam("id") Long id) throws BusinessLogicException {
        PromocionEntity entity = promocionLogic.getPromocion(catalogoId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + "/promocion/" + id + " no existe.", 404);
        }
        promocionLogic.deletePromocion(catalogoId, id);
    }
    
    private List<PromocionDTO> listEntity2DTO(List<PromocionEntity> entityList) {
        List<PromocionDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new PromocionDTO(entity));
        });
        return list;
    }
}
