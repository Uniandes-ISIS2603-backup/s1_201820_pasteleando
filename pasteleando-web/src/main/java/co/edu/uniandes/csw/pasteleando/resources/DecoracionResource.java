/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.DecoracionDTO;
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
 * <pre>Clase que implementa el recurso "Decoración ".
 * URL: /api/Decoración 
 */
@Path( "decoracion" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped

/**
 *
 * @author dc.cepeda
 */
public class DecoracionResource
{

	/**
	 * <h1>POST /api/decoracion : Crear una entidad de Decoración.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link DecoracionDTO}.
	 *
	 * Crea una nueva entidad de promoción con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de Decoración.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de Decoración.
	 * </code>
	 * </pre>
	 *
	 * @param dto {@link DecoracionDTO} - La entidad de decoracion que se desea guardar.
	 * @return JSON {@link DecoracionDTO}  - La entidad de decoracion guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de decoracion.
	 */
	@POST
	public DecoracionDTO createDecoracion( DecoracionDTO dto ) throws BusinessLogicException
	{
		return dto;
	}

	/**
	 * <h1>GET /api/decoracion : Obtener todas las entidadese de Decoración</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Decoración que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de Decoración de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link DecoracionDTO} - Las entidades de Decoración  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<DecoracionDTO> getDecoracion( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/decoracion/{id} : Obtener una entidad de Decoración  por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Decoración con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de Decoración correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de Decoración con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de decoracion que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link DecoracionDTO} - La entidad de decoracion buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public DecoracionDTO getDecoracion( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/decoracion/{id} : Actualizar una entidad de Decoración con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link DecoracionDTO}.
	 *
	 * Actualiza la entidad de Decoración con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de Decoración con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Decoración  con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de Decoración  que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link DecoracionDTO} La entidad de Decoración  que se desea guardar.
	 * @return JSON {@link DecoracionDTO} - La entidad de Decoración guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Decoración porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public DecoracionDTO updateDecoracion( @PathParam( "id" ) Long id, DecoracionDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/decoracion/{id} : Borrar una entidad de Decoración por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Decoración con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de Decoración correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Decoración  con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Decoración que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteDecoracion( @PathParam( "id" ) Long id )
	{
		// Void
	}
}

