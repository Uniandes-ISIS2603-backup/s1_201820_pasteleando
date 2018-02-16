/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.DecoracionPersonalizadaDTO;
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
 * <pre>Clase que implementa el recurso "Decoración Personalizada".
 * URL: /api/Decoración Personalizada
 */
@Path( "personalizada" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped

/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaResource
{

	/**
	 * <h1>POST /api/personalizadas : Crear una entidad de Decoración Personalizada.</h1>
	 * <pre>Cuerpo de petición: JSON {@link DecoracionPersonalizadaDTO}.
	 *
	 * Crea una nueva entidad de promoción con la informacion que se recibe en el cuerpo
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
	 * @param dto {@link DecoracionPersonalizadaDTO} - La entidad de promoción que se desea guardar.
	 * @return JSON {@link DecoracionPersonalizadaDTO}  - La entidad de promoción guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de promoción.
	 */
	@POST
	public DecoracionPersonalizadaDTO createDecoracionPersonalizada( DecoracionPersonalizadaDTO dto ) throws BusinessLogicException
	{
		return dto;
	}

	/**
	 * <h1>GET /api/personalizadas : Obtener todas las entidadese de Decoración Catálogo.</h1>
	 * <pre>Busca y devuelve todas las entidades de Decoración Personalizada que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de Decoración Personalizada de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link DecoracionPersonalizadaDTO} - Las entidades de Decoración Personalizada encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<DecoracionPersonalizadaDTO> getDecoracionPersonalizada( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/personalizadas/{id} : Obtener una entidad de Decoración personalizada por id.</h1>
	 * <pre>Busca la entidad de Decoración Personalizada con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de Decoración Personalizacion correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de Decoración Personalizada con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de promoción que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link DecoracionPersonalizadaDTO} - La entidad de promoción buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public DecoracionPersonalizadaDTO getDecoracionPersonalizada( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/personalizadas/{id} : Actualizar una entidad de Decoración Personalizada con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link DecoracionPersonalizadaDTO}.
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
	 * @param detailDTO {@link DecoracionPersonalizadaDTO} La entidad de Decoración Personalizada que se desea guardar.
	 * @return JSON {@link DecoracionPersonalizadaDTO} - La entidad de Decoración Personalizada guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Decoración Personalizada porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public DecoracionPersonalizadaDTO updateDecoracionPersonalizada( @PathParam( "id" ) Long id, DecoracionPersonalizadaDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/personalizadas/{id} : Borrar una entidad de Decoración Personalizada por id.</h1>
	 * 
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
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteDecoracionPersonalizada( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
