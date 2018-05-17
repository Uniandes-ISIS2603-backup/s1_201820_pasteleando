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

import co.edu.uniandes.csw.pasteleando.dtos.ClienteDTO;
import co.edu.uniandes.csw.pasteleando.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.pasteleando.ejb.ClienteLogic;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.mappers.BusinessLogicExceptionMapper;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * <pre>Clase que implementa el recurso "cliente".
 * URL: /api/cliente
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "cliente".</i>
 *
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {

    /**
     * <h1>POST /api/pasteleando : Crear una entidad de Calificacion.</h1>
     *
	 * <pre>Cuerpo de petición: JSON {@link ClienteDTO}.
     *
     * Crea una nueva entidad de Calificacion con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva entidad de Calificacion.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la entidad de Cliente.
     * </code>
     * </pre>
     *
     * @param dto {@link ClienteDTO} - La entidad de Cliente que se desea
     * guardar.
     * @return JSON {@link ClienteDTO} - La entidad de Cliente guardada con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entidad de Cliente.
     */
    @Inject
    ClienteLogic cliente;

    @POST
    public ClienteDetailDTO createCliente(ClienteDetailDTO dto) throws BusinessLogicException {
        ClienteDetailDTO dto1 = dto;
        dto1.setId(Long.MIN_VALUE);
        List<ClienteEntity> entity = cliente.getAll();

        for (int i = 0; i < entity.size(); i++) {
            ClienteEntity actual = entity.get(i);
            if (actual.getId() == dto1.getId()) {
                throw new BusinessLogicException("Ya existe una entidad de Cliente con el id dado");
            }

        }
        return new ClienteDetailDTO(cliente.create(dto.toEntity()));
    }

    /**
     * <h1>GET /api/pasteleando : Obtener todas las entidadese de Cliente.</h1>
     *
	 * <pre>Busca y devuelve todas las entidades de Cliente que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las entidades de Cliente de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link ClienteDTO} - Las entidades de Cliente
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ClienteDetailDTO> getClientes() {
        List<ClienteEntity> lista = cliente.getAll();
        List<ClienteDetailDTO> listaNueva = new ArrayList<ClienteDetailDTO>();
        for (int i = 0; i < lista.size(); i++) {
            listaNueva.add(new ClienteDetailDTO(lista.get(i)));

        }
        return listaNueva;
    }

    /**
     * <h1>GET /api/pasteleando/{id} : Obtener una entidad de Cliente por
     * id.</h1>
     *
	 * <pre>Busca la entidad de Cliente con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la entidad de Cliente correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una entidad de Cliente con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de Cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - La entidad de Cliente buscada
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) throws BusinessLogicException {
        ClienteEntity entity = cliente.getById(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }

        return new ClienteDetailDTO(entity);
    }

    /**
     * <h1>PUT /api/pasteleando/{id} : Actualizar una entidad de Cliente con el
     * id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ClienteDTO}.
     *
     * Actualiza la entidad de Cliente con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la entidad de Cliente con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Cliente con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la entidad de Cliente que se desea
     * actualizar.Este debe ser una cadena de dígitos.
     * @param detailDTO {@link ClienteDTO} La entidad de Cliente que se desea
     * guardar.
     * @return JSON {@link ClienteDTO} - La entidad de Cliente guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la entidad de
     * Cliente porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id, ClienteDetailDTO detailDTO) throws BusinessLogicException {
        detailDTO.setId(id);
        ClienteEntity entity = cliente.getById(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        return new ClienteDetailDTO(cliente.update(detailDTO.toEntity()));

    }

    /**
     * <h1>DELETE /api/pasteleando/{id} : Borrar una entidad de Cliente por
     * id.</h1>
     *
	 * <pre>Borra la entidad de Cliente con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la entidad de Cliente correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una entidad de Cliente con el id dado.
     * </code>
     * </pre>
     *
     *
     * @param id Identificador de la entidad de Cliente que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCliente(@PathParam("id") Long id) throws BusinessLogicException {
        ClienteEntity entity = cliente.getById(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);

        }
        try {
            cliente.delete(entity);
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
        }

    }
}
