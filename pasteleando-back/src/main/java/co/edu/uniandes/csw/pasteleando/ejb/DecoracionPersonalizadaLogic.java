/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;


import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersonalizadaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dc.cepeda
 */
@Stateless
public class DecoracionPersonalizadaLogic 
{ 
    private static final Logger LOGGER = Logger.getLogger(DecoracionCatalogoLogic.class.getName());

    @Inject
    private DecoracionPersonalizadaPersistence persistence;

   
    
/**
     * Devuelve todos las decoraciones personalizadas que hay en la base de datos.
     * @return Lista de entidades de tipo decoracion personalizada.
     */
    public List<DecoracionPersonalizadaEntity> getDecoracionesPersonalizadas() {
        LOGGER.info( "Inicia proceso de consultar todas las entidades de Decoracion Personalizada" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<DecoracionPersonalizadaEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Decoracion Personalizada" );
		return entities;
    }
    /**
     * Busca una decoración personalizada por ID
     * @param id El id de la decoración personalizada a buscar
     * @return La decoración personalizada encontrada, null si no la encuentra.
     */
    public DecoracionPersonalizadaEntity getDecoracionPersonalizada(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar decoración personalizada con id={0}", id);
        DecoracionPersonalizadaEntity decoracion = persistence.find(id);
        if (decoracion == null) {
            LOGGER.log(Level.SEVERE, "La decoración personalizada con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la decoración personalizada con id={0}", id);
        return decoracion;
    }

    /**
     * Guardar una nueva decoración personalizada
     * @param entity La entidad de tipo decoración personalizada de la nueva decoración  a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN ya existe en la persitencia.
     */
    public DecoracionPersonalizadaEntity createDecoracionPersonalizada(DecoracionPersonalizadaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de decoración personalizada");
        if (!validatePeso(entity.getPeso())) {
            throw new BusinessLogicException("El peso del pastel no es valido. Debe ser mayor o igual a 0");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de la decoración persnalizada");
        return entity;
    }

    /**
     * Actualizar una decoración personalizada por ID
     * @param id El ID de la decoración personalizada a actualizar
     * @param entity La entidad de la decoración personalizada con los cambios deseados
     * @return La entidad de la decoración personalizada luego de actualizarla
     * @throws BusinessLogicException Si la foto de la actualización es inválida
     */
    public DecoracionPersonalizadaEntity updateDecoracionPersonalizada(Long id, DecoracionPersonalizadaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar decoración personalizada con id={0}", id);
        if (!validatePeso(entity.getPeso())) {
            throw new BusinessLogicException("El peso del pastel no es valido. Debe ser mayor o igual a 0");
        }
        DecoracionPersonalizadaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar decoración personalizada con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Eliminar una decoración personalizada por ID
     * @param id El ID de la decoración personalizada a eliminar
     */
    public void deleteDecoracionPersonalizada(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar decoración personalizada con id={0}", id);
        if(persistence.find(id)==null)
        {
            throw new BusinessLogicException("No existe una decoración personalizada con ese id");
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar decoración personalizada con id={0}", id);
    }

    private boolean validatePeso(int peso) {
        if(peso <=0)
            return false;
        else
            return true;
    }
    
    
    }
