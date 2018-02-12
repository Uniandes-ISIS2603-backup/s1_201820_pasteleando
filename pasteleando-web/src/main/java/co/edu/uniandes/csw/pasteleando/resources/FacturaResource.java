/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.pasteleando.dtos.FacturaDetailDTO;

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
@Path( "factura" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class FacturaResource {

    /**
	 * <h1>POST /api/factura : Crear una entidad de Factura.</h1>
	 * <p>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
	 *
	 * Crea una nueva entidad de factura con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de Factura.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de Factura.
	 * </code>
	 * </pre>
	 *
	 * @param dto {@link FacturaDetailDTO} - La entidad de Factura que se desea guardar.
	 * @return JSON {@link FacturaDetailDTO}  - La entidad de Factura guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de Pasteleando.
	 */
        @POST
	public FacturaDetailDTO createFactura( FacturaDetailDTO dto ) throws BusinessLogicException
	{
		return dto;
	}
        
        /**
	 * <h1>GET /api/factura : Obtener todas las entidadese de Factura.</h1>
	 * <p>
	 * <pre>Busca y devuelve todas las entidades de Factura que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de Factura de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link FacturaDetailDTO} - Las entidades de Factura encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<FacturaDetailDTO> getFactura( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/factura/{id} : Obtener una entidad de Factura por id.</h1>
	 * <p>
	 * <pre>Busca la entidad de Factura con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de Factura correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de Factura con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Factura que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link FacturaDetailDTO} - La entidad de Factura buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public FacturaDetailDTO getFactura( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/factura/{id} : Actualizar una entidad de Factura con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
	 *
	 * Actualiza la entidad de Factura con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de Factura con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Factura con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de Factura que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link FacturaDetailDTO} La entidad de Factura que se desea guardar.
	 * @return JSON {@link FacturaDetailDTO} - La entidad de Factura guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de Pasteleando porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public FacturaDetailDTO updateFactura( @PathParam( "id" ) Long id, FacturaDetailDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/factura/{id} : Borrar una entidad de Factura por id.</h1>
	 * <p>
	 * <pre>Borra la entidad de Factura con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de Factura correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de Factura con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de Factura que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteFactura( @PathParam( "id" ) Long id )
	{
		// Void
	}
}
