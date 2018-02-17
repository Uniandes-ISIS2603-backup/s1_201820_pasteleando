/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.resources;

import co.edu.uniandes.csw.pasteleando.dtos.CarritoDetailDTO;
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
 * @author MIGUELHOYOS
 */
@Path("carritos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource 
{
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
     */
    @POST
    public CarritoDetailDTO createCity(CarritoDetailDTO carrito)  {
        return carrito;
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
        return new ArrayList<>();
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
    public CarritoDetailDTO getCarrito(@PathParam("id") Long id) {
        return null;
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
     * @param carrito {@link CityDetailDTO} el carrito que se desea guardar.
     * @return JSON {@link CarritoDetailDTO} - el carrito guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public CarritoDetailDTO updateCity(@PathParam("id") Long id, CarritoDetailDTO carrito)  {
        return carrito;
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
     public void deleteCity(@PathParam("id") Long id) {
        // Void
    }

}
