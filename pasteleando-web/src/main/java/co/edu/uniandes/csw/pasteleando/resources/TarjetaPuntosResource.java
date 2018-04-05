/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.TarjetaPuntosDTO;
import co.edu.uniandes.csw.pasteleando.dtos.TarjetaPuntosDetailDTO;
import co.edu.uniandes.csw.pasteleando.ejb.TarjetaPuntosLogic;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
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
 */
@Path( "/cliente/{idCliente: \\d+}/tarjetapuntos" )
@Produces("application/json")
@Consumes("application/json" )
@RequestScoped
public class TarjetaPuntosResource {
    
    
        @Inject
        private TarjetaPuntosLogic tarjetaLogic;
    
        @Inject
        private ClienteResource clienteLogic;
        
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
	 * @throws BusinessLogicException  Error de lógica que se genera cuando ya existe la entidad de TarjetaPuntos.
	 */
	@POST
	public TarjetaPuntosDTO createTarjetaPuntos( TarjetaPuntosDetailDTO dto, @PathParam("idCliente") Long idCliente ) throws BusinessLogicException
	{
            if (clienteLogic.getCliente(idCliente) == null)
            {
                throw new WebApplicationException("El cliente no existe");
            }
		return new TarjetaPuntosDTO(tarjetaLogic.createTarjetaPuntos(idCliente, dto.toEntity()));
	}
        
        private List<TarjetaPuntosDTO> listEntity2DTO(List<TarjetaPuntosEntity> entityList) {
        List<TarjetaPuntosDTO> list = new ArrayList<>();
        for (TarjetaPuntosEntity entity : entityList) {
            list.add(new TarjetaPuntosDTO(entity));
        }
        return list;
        }

        @GET
        @Path("/all")
        public List<TarjetaPuntosDTO> getTarjetasPuntos(){
            return listEntity2DTO(tarjetaLogic.getTarjetasPuntos());
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
        @Path( "{idTarjeta: \\d+}" )
	public TarjetaPuntosDTO getTarjetaPuntos(@PathParam("idCliente") Long idCliente,@PathParam("id") Long idTarjeta ) throws WebApplicationException
	{
            TarjetaPuntosEntity entity = tarjetaLogic.getTarjetaPuntos(idCliente, idTarjeta);
        if (entity == null) {
            throw new WebApplicationException("El recurso no existe", 404);
        }
        return new TarjetaPuntosDTO(entity);
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
	 * @throws BusinessLogicException Error de lógica que se genera al no poder actualizar la entidad de TarjetaPuntos porque ya existe una con ese nombre.
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public TarjetaPuntosDTO updateTarjetaPuntos( @PathParam( "id" ) Long idTarjeta, @PathParam("idCliente") Long idCliente , TarjetaPuntosDetailDTO detailDTO ) throws BusinessLogicException
	{
            
            if(clienteLogic.getCliente(idCliente) == null)
            {
                throw new WebApplicationException("404, No se encontró la(s) tarjetas del cliente");
            }
            
            if(tarjetaLogic.getTarjetaPuntos(idCliente,idTarjeta) == null)
            {
                throw new WebApplicationException("404, No se encontró la tarjeta del cliente");
            }
            
		detailDTO.setId(idTarjeta);
                TarjetaPuntosEntity entity = detailDTO.toEntity();
                entity.setId(idTarjeta);
                return new TarjetaPuntosDTO(tarjetaLogic.updateTarjetaPuntos(idCliente,entity));
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
	public void deleteTarjetaPuntos( @PathParam( "id" ) Long id , @PathParam("idCliente") Long idCliente)
	{
            
            if(clienteLogic.getCliente(idCliente) == null)
            {
                throw new WebApplicationException("404, No se encontró la(s) tarjetas del cliente");
            }
                tarjetaLogic.deleteTarjetaPuntos(idCliente,id);
	}
}
