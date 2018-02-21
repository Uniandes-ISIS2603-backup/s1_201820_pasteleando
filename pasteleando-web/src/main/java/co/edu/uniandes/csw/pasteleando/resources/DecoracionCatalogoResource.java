
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.DecoracionCatalogoDTO;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.mappers.BusinessLogicExceptionMapper;

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
 * @author ISIS2603
 * @version 1.0
 */
@Path( "catalogo" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class DecoracionCatalogoResource
{

	/**
	 * <h1>POST /api/catalogo : Crear una entidad de Decoración Catálogo.</h1>
	 * <pre>Cuerpo de petición: JSON {@link DecoracionCatalogoDTO}.
	 *
	 * Crea una nueva entidad de promoción con la informacion que se recibe en el cuerpo
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
	 * @param dto {@link DecoracionCatalogoDTO} - La entidad de promoción que se desea guardar.
	 * @return JSON {@link DecoracionCatalogoDTO}  - La entidad de promoción guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de promoción.
	 */
	@POST
	public DecoracionCatalogoDTO createDecoracionCatalogo( DecoracionCatalogoDTO dto ) throws BusinessLogicException
	{
		return dto;
	}

	/**
	 * <h1>GET /api/catalogo : Obtener todas las entidadese de Decoración Catálogo.</h1>
	 * <pre>Busca y devuelve todas las entidades de Decoración Catálogo que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de Decoración Catálogo de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link DecoracionCatalogoDTO} - Las entidades de Decoración Catálogo encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<DecoracionCatalogoDTO> getDecoracionCatalogo( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/catalogo/{id} : Obtener una entidad de Decoración Catálogo por id.</h1>
	 * <pre>Busca la entidad de Decoración Catálogo con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de Decoración Catálogo correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de Decoración Catálogo con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de promoción que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link DecoracionCatalogoDTO} - La entidad de promoción buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public DecoracionCatalogoDTO getDecoracionCatalogo( @PathParam( "id" ) Long id )
	{
		return null;
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
	 * @param detailDTO {@link DecoracionCatalogoDTO} La entidad de Decoración Catálogo que se desea guardar.
	 * @return JSON {@link DecoracionCatalogoDTO} - La entidad de Decoración Catálogo guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Decoración Catálogo porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public DecoracionCatalogoDTO updateDecoracionCatalogo( @PathParam( "id" ) Long id, DecoracionCatalogoDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
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
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteDecoracionCatalogo( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
