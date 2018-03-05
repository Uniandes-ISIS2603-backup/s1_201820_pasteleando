/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.pasteleando.ejb.CarritoLogic;
import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Iterator;
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
 *<pre> clase que implementa elrecurso "carritos"
 * URL: /api/carritos
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "carritos".</i>
 * 
 * <h2> Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * 
 * @author MIGUELHOYOS
 */
@Path("carritos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource 
{
    
    @Inject
    private CarritoLogic carritoLogic
            
      /**
     * <h1>POST /api/carritos : Crear un carrito.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     * 
     * Crea un nuevo carrito con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el carrito nuevo .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el carrito.
     * </code>
     * </pre>
     * @param carrito {@link CarritoDetailDTO} - el carrito que se desea guardar.
     * @return JSON {@link CarritoDetailDTO}  - el carrito guardado con el atributo id autogenerado.
     * @throws BusinessLogicException
     */
    @POST
    public CarritoDetailDTO createCarrito(CarritoDetailDTO carrito) throws BusinessLogicException  {
        return new CarritoDetailDTO(carritoLogic.createCarrito(carrito.toEntity()));
    }

    /**
     * <h1>GET /api/cities : Obtener todas las ciudades.</h1>
     * 
     * <pre>Busca y devuelve todos los carritos que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los carritos de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link CarritoDetailDTO} - Los carritos encontradas en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CarritoDetailDTO> getCarritos() {
        List<CarritoDetailDTO> rta = new ArrayList<>();
        List lista = carritoLogic.findCarritos();
        Iterator ite =  lista.iterator();
        while(ite.hasNext())
        {
            CarritoDetailDTO dto = new CarritoDetailDTO((CarritoEntity) ite.next());
            rta.add(dto);
        }
        return rta;
    }

    /**
     * <h1>GET /api/carritos/{id} : Obtener carrito por id.</h1>
     * 
     * <pre>Busca el carrito con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el carrito correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un carrito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del carrito que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link CarritoDetailDTO} - el carrito buscado
     */
    @GET
    @Path("{id: \\d+}")
    public CarritoDetailDTO getCarrito(@PathParam("id") Long id) throws BusinessLogicException {
        return new CarritoDetailDTO(carritoLogic.findCarrito(id));
    }
    
    /**
     * <h1>PUT /api/carritos/{id} : Actualizar el carrito con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     * 
     * Actualiza el carrito con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el carrito con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un carrito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del carrito que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param carrito {@link CarritoDetailDTO} el carrito que se desea guardar.
     * @return JSON {@link CarritoDetailDTO} - el carrito guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public CarritoDetailDTO updateCarrito(@PathParam("id") Long id, CarritoDetailDTO carrito)throws WebApplicationException, BusinessLogicException  {
       
        CarritoEntity entity = carritoLogic.findCarrito(id);
        
        if(entity.equals(null)){
            throw new WebApplicationException("\"El recurso /carrito/" + id + " no existe." , 404);
        }
        else{
            return new CarritoDetailDTO(carritoLogic.updateCarrito(carrito.toEntity()));
        }
    }
    
    /**
     * <h1>DELETE /api/carritos/{id} : Borrar el carrito por id.</h1>
     * 
     * <pre>Borra el carrito con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el carrito correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un carrito con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del carrito que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteCarrito(@PathParam("id") Long id) throws BusinessLogicException {
       CarritoEntity entity = carritoLogic.findCarrito(id);
       if(entity.equals(null))
       {
           throw new WebApplicationException("El recurso /carrito/" + id + " no existe.", 404);
       }
       else{
           carritoLogic.deleteCarrito(id);
       }
    }

}
