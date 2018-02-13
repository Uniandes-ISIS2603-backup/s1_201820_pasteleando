/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.TarjetaPuntosDetailDTO;
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
 *
 * @author m.leona
 */
/**
 * <pre>Clase que implementa el recurso "TarjetaPuntos".
 * URL: /api/TarjetaPuntos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "TarjetaPuntos".</i>
 * 
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
@Path( "tarjetapuntos" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class TarjetaPuntosResource {
    
	/**
	 * <h1>POST /api/TarjetaPuntos : Crear una entidad de TarjetaPuntos.</h1>
	 * 
	 * <pre>Cuerpo de petición: JSON {@link TarjetaPuntosDetailDTO}.
	 *
	 * Crea una nueva entidad de TarjetaPuntos con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de TarjetaPuntos.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de TarjetaPuntos.
	 * </code>
	 * </pre>
	 *
	 * @param dto {@link TarjetaPuntosDetailDTO} - La entidad de TarjetaPuntos que se desea guardar.
	 * @return JSON {@link TarjetaPuntosDetailDTO}  - La entidad de TarjetaPuntos guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de TarjetaPuntos.
	 */
	@POST
	public TarjetaPuntosDetailDTO createTarjetaPuntos( TarjetaPuntosDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}

	/**
	 * <h1>GET /api/TarjetaPuntos : Obtener todas las entidadese de TarjetaPuntos.</h1>
	 * 
	 * <pre>Busca y devuelve todas las entidades de TarjetaPuntos que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de TarjetaPuntos de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link TarjetaPuntosDetailDTO} - Las entidades de TarjetaPuntos encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<TarjetaPuntosDetailDTO> getTarjetaPuntos( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/TarjetaPuntos/{id} : Obtener una entidad de TarjetaPuntos por id.</h1>
	 * 
	 * <pre>Busca la entidad de TarjetaPuntos con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de TarjetaPuntos correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de TarjetaPuntos con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de TarjetaPuntos que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link TarjetaPuntosDetailDTO} - La entidad de TarjetaPuntos buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public TarjetaPuntosDetailDTO getTarjetaPuntos( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/TarjetaPuntos/{id} : Actualizar una entidad de TarjetaPuntos con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link TarjetaPuntosDetailDTO}.
	 *
	 * Actualiza la entidad de TarjetaPuntos con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de TarjetaPuntos con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de TarjetaPuntos con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de TarjetaPuntos que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link TarjetaPuntosDetailDTO} La entidad de TarjetaPuntos que se desea guardar.
	 * @return JSON {@link TarjetaPuntosDetailDTO} - La entidad de TarjetaPuntos guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de TarjetaPuntos porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public TarjetaPuntosDetailDTO updateTarjetaPuntos( @PathParam( "id" ) Long id, TarjetaPuntosDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/TarjetaPuntos/{id} : Borrar una entidad de TarjetaPuntos por id.</h1>
	 * 
	 * <pre>Borra la entidad de TarjetaPuntos con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de TarjetaPuntos correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de TarjetaPuntos con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de TarjetaPuntos que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteTarjetaPuntos( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
