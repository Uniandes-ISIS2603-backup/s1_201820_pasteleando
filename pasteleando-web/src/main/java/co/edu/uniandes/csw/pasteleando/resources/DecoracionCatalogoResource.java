
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.DecoracionCatalogoDTO;
import co.edu.uniandes.csw.pasteleando.dtos.DecoracionCatalogoDetailDTO;
import co.edu.uniandes.csw.pasteleando.ejb.DecoracionCatalogoLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
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
 * <pre>Clase que implementa el recurso "Decoración catálogo".
 * URL: /api/Decoración catálogo
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "Decoración catálogo".</i>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author jf.garcia
 */
@Path( "catalogo" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class DecoracionCatalogoResource
{
    
    @Inject
            DecoracionCatalogoLogic decoracionCatalogoLogic;
    
    /**
     * <h1>GET /api/catalogo : Obtener todas las entidades de decoración del catálogo.</h1>
     * <pre>Busca y devuelve todas las entidades de decoración del catálogo que existen en la aplicación.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las entidades de decoración del catálogo de la aplicación.</code>
     * </pre>
     *
     * @return JSONArray {@link DecoracionCatalogoDTO} - Las entidades de decoración del catálogo encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<DecoracionCatalogoDetailDTO> getDecoracionesCatalogo() {
        return listDecoracionCatalogoEntity2DetailDTO(decoracionCatalogoLogic.getDecoracionesCatalogo());
    }
    
    /**
     * <h1>GET /api/catalogo/{id} : Obtener una entidad de Decoración Catálogo por id.</h1>
     * <pre>Busca la entidad de decoración del catálogo con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la entidad de decoración del catálogo correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una entidad de decoración del catálogo con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de decoración del catálogo que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link DecoracionCatalogoDTO} - La entidad de decoración del catálogo buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public DecoracionCatalogoDetailDTO getDecoracionCatalogo(@PathParam("id") Long id) {
        DecoracionCatalogoEntity entity = decoracionCatalogoLogic.getDecoracionCatalogo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        return new DecoracionCatalogoDetailDTO(entity);
    }
    
    /**
     * <h1>POST /api/catalogo : Crear una entidad de Decoración Catálogo.</h1>
     * <pre>Cuerpo de petición: JSON {@link DecoracionCatalogoDTO}.
     *
     * Crea una nueva entidad de decoración del catálogo con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Decoración Catálogo.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Decoración Catálogo.
     * </code>
     * </pre>
     *
     * @param decoracionCatalogo {@link DecoracionCatalogoDTO} - La entidad de la decoración del catálogo que se desea guardar.
     * @return JSON {@link DecoracionCatalogoDTO}  - La entidad de decoración del catálogo guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de decoración del catálogo.
     */
    @POST
    public DecoracionCatalogoDetailDTO createDecoracionCatalogo(DecoracionCatalogoDetailDTO decoracionCatalogo) throws BusinessLogicException {
        return new DecoracionCatalogoDetailDTO(decoracionCatalogoLogic.createDecoracionCatalogo(decoracionCatalogo.toEntity()));
    }
    
    /**
     * <h1>PUT /api/catalogo/{id} : Actualizar una entidad de Decoración Catálogo con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link DecoracionCatalogoDTO}.
     *
     * Actualiza la entidad de Decoración Catálogo con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la entidad de Decoración Catálogo con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Decoración Catálogo con el id dado.
     * </code>
     * </pre>
     *
     * @param id        Identificador de la entidad de Decoración Catálogo que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param decoracionCatalogo {@link DecoracionCatalogoDTO} La entidad de Decoración Catálogo que se desea guardar.
     * @return JSON {@link DecoracionCatalogoDTO} - La entidad de Decoración Catálogo guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Decoración Catálogo porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public DecoracionCatalogoDetailDTO updateDecoracionCatalogo(@PathParam("id") Long id, DecoracionCatalogoDetailDTO decoracionCatalogo) throws WebApplicationException, BusinessLogicException {
        decoracionCatalogo.setId(id);
        DecoracionCatalogoEntity entity = decoracionCatalogoLogic.getDecoracionCatalogo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + id + " no existe.", 404);
        }
        return new DecoracionCatalogoDetailDTO(decoracionCatalogoLogic.updateDecoracionCatalogo(id, decoracionCatalogo.toEntity()));
    }
    
    /**
     * <h1>DELETE /api/catalogo/{id} : Borrar una entidad de Decoración Catálogo por id.</h1>
     * <pre>Borra la entidad de Decoración Catálogo con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la entidad de Decoración Catálogo correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Decoración Catálogo con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de Decoración Catálogo que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la decoración del catálogo.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDecoracionCatalogo(@PathParam("id") Long id) throws BusinessLogicException {
        DecoracionCatalogoEntity entity = decoracionCatalogoLogic.getDecoracionCatalogo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        decoracionCatalogoLogic.deleteDecoracionCatalogo(id);
    }
    
    /**
     * Conexión con el servicio de promociones para una decoración del catálogo. {@link PromocionResource}
     *
     * Este método conecta la ruta de /catalogo con las rutas de /promociones que dependen
     * de la decoración del catálogo, es una redirección al servicio que maneja el segmento de la
     * URL que se encarga de las promociones.
     *
     * @param catalogoId El ID de la decoración del catálogo con respecto al cual se accede al servicio.
     * @return El servicio de promociones para la decoración del catálogo en paricular.
     */
    @Path("{id: \\d+}/reviews")
    public Class<PromocionResource> getPromocionResource(@PathParam("id") Long catalogoId) {
        DecoracionCatalogoEntity entity = decoracionCatalogoLogic.getDecoracionCatalogo(catalogoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + "/promocion no existe.", 404);
        }
        return PromocionResource.class;
    }
    
    private List<DecoracionCatalogoDetailDTO> listDecoracionCatalogoEntity2DetailDTO(List<DecoracionCatalogoEntity> entityList) {
        List<DecoracionCatalogoDetailDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new DecoracionCatalogoDetailDTO(entity));
        });
        return list;
    }
}
