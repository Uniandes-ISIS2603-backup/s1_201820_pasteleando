/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PqrsPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Pqrs.
 * @author ni.ramirez10
 */

@Stateless
public class PqrsLogic
{
    private static final Logger LOGGER = Logger.getLogger( PqrsLogic.class.getName());
    
    @Inject
    private PqrsPersistence persistence;
    
    /**
     * Devuelve la lista de pqrs
     * @return Colección de objetos de PqrsEntity
     */
    
    public List<PqrsEntity> getPqrs()
    {
        LOGGER.info("Inicia proceso de consultar todas las pqrs");
        List<PqrsEntity> pqrs = persistence.findAll();
        
        return pqrs;
    }
    
    /**
     * Busca un pqrs a partir de su identificador (id)
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PqrsEntity con los datos del pqrs consultado.
     */
    
    public PqrsEntity getPqrs(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un pqrs con id = {0}", id);
        return persistence.find(id);
    }
    
    public boolean validarReglasDeNegocio(Integer id_solicitud, Integer id_tipo)
    {
        return !(id_solicitud >0 && id_tipo > 0 && id_solicitud == null && id_tipo ==null);
    }
    
    /**
     * Crea una nueva pqrs.
     * @param entity La entidad de tipo pqrs a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el identificador ya existe en la persitencia.
     */
    
    public PqrsEntity createPqrs(PqrsEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de la pqrs");
        persistence.create(entity);
        if(!validarReglasDeNegocio(entity.getIdSolicitud(), entity.getTipo()))
        {
            throw new BusinessLogicException("El tipo de solicitud o el tipo son invalidos");
        }
        LOGGER.info("Termina proceso de creación de la pqrs");
        
        return entity;
    }
    
    /**
     * Actualizar una pqrs a partir del identificador (id)
     * @param id El ID de la pqrs a actualizar
     * @param entity La entidad de pqrs con los cambios deseados
     * @return La entidad de pqrs luego de actualizarla
     * @throws BusinessLogicException Si el identificador ya existe en la persitencia.
     */
    
    public PqrsEntity updatePqrs(Long id, PqrsEntity entity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la pqrs con id ={0}", id);
        PqrsEntity pEntity = persistence.update(entity);
        if(!validarReglasDeNegocio(entity.getIdSolicitud(), entity.getTipo()))
        {
            throw new BusinessLogicException("El tipo de solicitud o el tipo son invalidos");
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la pqrs con id ={0}", entity.getId());
        
        return pEntity;
    }
    
    /**
     * Eliminar una pqrs a partir del identificador (id)
     * @param id El ID de la pqrs a eliminar
     */
    
    public void deletPqrs(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la pqrs con id ={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la pqrs con id ={0}", id);
    }
    
    
}
