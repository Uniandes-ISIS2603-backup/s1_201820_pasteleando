/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.PqrsDTO; 
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
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "pqrs".
 * URL: /api/pqrs
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "pqrs".</i>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author ni.ramirez10
 * @version 1.0
 */
@Path( "pqrs" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
@RequestScoped


public class PqrsResource 
{
    
    @Inject
    //private PqrsLogic pqrsLogic; 
        
        
    /**
	 * <h1>POST /api/pedido : Crear una entidad de pqrs.</h1>
	 * <pre>Cuerpo de petición: JSON {@link PqrsDTO}.
	 *
	 * Crea una nueva entidad de pqrs con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de pqrs.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de pqrs.
	 * </code>
	 * </pre>
	 *
	 * @param pPqrs {@link PqrsDTO} - La entidad de pqrs que se desea guardar.
	 * @return JSON {@link PqrsDTO}  - La entidad de pqrs guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de promoción.
	 */
	@POST
	public PqrsDTO createPqrs( PqrsDTO pPqrs ) throws BusinessLogicException
	{
		return pPqrs;
	}

	/**
	 * <h1>GET /api/pqrs : Obtener todas las entidadese de pqrs.</h1>
	 * <pre>Busca y devuelve todas las entidades de pqrs que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de pqrs de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link PqrsDTO} - Las entidades de pqrs encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<PqrsDTO> getPqrs( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/pqrs/{id} : Obtener una entidad de pqrs por id.</h1>
	 * <pre>Busca la entidad de pqrs con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de pqrs correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de pqrs con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de pqrs que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link PqrsDTO} - La entidad de pedido buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public PqrsDTO getPqrs( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/pqrs/{id} : Actualizar una entidad de pqrs con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link PqrsDTO}.
	 *
	 * Actualiza la entidad de pqrs con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de pqrs con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de pqrs con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de pqrs que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link PqrsDTO} La entidad de pqrs que se desea guardar.
	 * @return JSON {@link PqrsDTO} - La entidad de pqrs guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de pqrs porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public PqrsDTO updatePqrs( @PathParam( "id" ) Long id, PqrsDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/pqrs/{id} : Borrar una entidad de pqrs por id.</h1>
	 * <pre>Borra la entidad de pqrs con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de pqrs correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de pqrs con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de pqrs que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deletePqrs( @PathParam( "id" ) Long id )
	{
		// Void
	}
    
}
