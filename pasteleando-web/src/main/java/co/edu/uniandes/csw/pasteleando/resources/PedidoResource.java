/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.PedidoDTO; 
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
 * <pre>Clase que implementa el recurso "pedido".
 * URL: /api/pedido
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "pedido".</i>
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
@Path( "pedido" )
@Produces( "application/json" )
@Consumes( "application/json" )
@RequestScoped
public class PedidoResource 
{
    /**
	 * <h1>POST /api/pedido : Crear una entidad de pedido.</h1>
	 * <pre>Cuerpo de petición: JSON {@link PedidoDTO}.
	 *
	 * Crea una nueva entidad de pedido con la informacion que se recibe en el cuerpo
	 * de la petición y se regresa un objeto identico con un id auto-generado
	 * por la base de datos.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Creó la nueva entidad de Pedido.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 412 Precodition Failed: Ya existe la entidad de pedido.
	 * </code>
	 * </pre>
	 *
	 * @param pPedido {@link PedidoDTO} - La entidad de pedido que se desea guardar.
	 * @return JSON {@link PedidoDTO}  - La entidad de pedido guardada con el atributo id autogenerado.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la entidad de promoción.
	 */
	@POST
	public PedidoDTO createPedido( PedidoDTO pPedido ) throws BusinessLogicException
	{
		return pPedido;
	}

	/**
	 * <h1>GET /api/pedido : Obtener todas las entidadese de pedido.</h1>
	 * <pre>Busca y devuelve todas las entidades de pedido que existen en la aplicacion.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve todas las entidades de pedido de la aplicacion.</code>
	 * </pre>
	 *
	 * @return JSONArray {@link PedidoDTO} - Las entidades de pedido encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
	 */
	@GET
	public List<PedidoDTO> getPedido( )
	{
		return new ArrayList<>( );
	}

	/**
	 * <h1>GET /api/pedido/{id} : Obtener una entidad de pedido por id.</h1>
	 * <pre>Busca la entidad de pedido con el id asociado recibido en la URL y la devuelve.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Devuelve la entidad de pedido correspondiente al id.
	 * </code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found No existe una entidad de pedido con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de pedido que se esta buscando. Este debe ser una cadena de dígitos.
	 * @return JSON {@link PedidoDTO} - La entidad de pedido buscada
	 */
	@GET
	@Path( "{id: \\d+}" )
	public PedidoDTO getPedido( @PathParam( "id" ) Long id )
	{
		return null;
	}

	/**
	 * <h1>PUT /api/pedido/{id} : Actualizar una entidad de pedido con el id dado.</h1>
	 * <pre>Cuerpo de petición: JSON {@link PedidoDTO}.
	 *
	 * Actualiza la entidad de pedido con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
	 *
	 * Codigos de respuesta:
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Actualiza la entidad de pedido con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de pedido con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id        Identificador de la entidad de pedido que se desea actualizar.Este debe ser una cadena de dígitos.
	 * @param detailDTO {@link PedidoDTO} La entidad de pedido que se desea guardar.
	 * @return JSON {@link PedidoDTO} - La entidad de pedido guardada.
	 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la entidad de pedido porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public PedidoDTO updatePedido( @PathParam( "id" ) Long id, PedidoDTO detailDTO ) throws BusinessLogicException
	{
		return detailDTO;
	}

	/**
	 * <h1>DELETE /api/pedido/{id} : Borrar una entidad de pedido por id.</h1>
	 * <pre>Borra la entidad de pedido con el id asociado recibido en la URL.
	 *
	 * Códigos de respuesta:<br>
	 * <code style="color: mediumseagreen; background-color: #eaffe0;">
	 * 200 OK Elimina la entidad de pedido correspondiente al id dado.</code>
	 * <code style="color: #c7254e; background-color: #f9f2f4;">
	 * 404 Not Found. No existe una entidad de pedido con el id dado.
	 * </code>
	 * </pre>
	 *
	 * @param id Identificador de la entidad de pedido que se desea borrar. Este debe ser una cadena de dígitos.
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deletePedido( @PathParam( "id" ) Long id )
	{
		// Void
	}
    
    
}
