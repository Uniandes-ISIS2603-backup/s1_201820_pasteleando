/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
CITYS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.CalificacionDTO;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.mappers.BusinessLogicExceptionMapper;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>Clase que implementa el recurso "calificacion".
 * URL: /api/calificacion
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "calificacion".</i>
 * <p>
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author mp.bayonal
 * @version 1.0
 */
@Path( "calificaciones" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class CalificacionResource
{

	/**
	 * <h1>POST /api/calificacion : Crear una entidad de calificacion.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link CalificacionDTO}.
	 *
	 * Crea una nueva entidad de Pasteleando con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de Pasteleando.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de Pasteleando.
	 * </code>
	 * </pre>
	 *
	 * @param dto {@link CalificacionDTO} - La entidad de Pasteleando que se desea guardar.
	 * @return JSON {@link CalificacionDTO}  - La entidad de Pasteleando guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Pasteleando.
	 */
	@POST
	public CalificacionDTO createCalificacion( CalificacionDTO dto ) throws BusinessLogicException
	{
		return dto;
	}

	/**
	 * <h1>GET /api/pasteleando : Obtener todas las entidadese de Pasteleando.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Pasteleando que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de Pasteleando de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link CalificacionDTO} - Las entidades de Pasteleando encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<CalificacionDTO> getCalificacion( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/pasteleando/{id} : Obtener una entidad de Pasteleando por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Pasteleando con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de Pasteleando correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de Pasteleando con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Pasteleando que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link CalificacionDTO} - La entidad de Pasteleando buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public CalificacionDTO getCalificacion( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/pasteleando/{id} : Actualizar una entidad de Pasteleando con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link CalificacionDTO}.
	 *
	 * Actualiza la entidad de Pasteleando con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de Pasteleando con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Pasteleando con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de Pasteleando que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link CalificacionDTO} La entidad de Pasteleando que se desea guardar.
	 * @return JSON {@link CalificacionDTO} - La entidad de Pasteleando guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Pasteleando porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public CalificacionDTO updateCalificacion( @PathParam( "id" ) Long id, CalificacionDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/pasteleando/{id} : Borrar una entidad de Cliente por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Cliente con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de Cliente correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Calificacion con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Calificacion que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteCalificacion( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
