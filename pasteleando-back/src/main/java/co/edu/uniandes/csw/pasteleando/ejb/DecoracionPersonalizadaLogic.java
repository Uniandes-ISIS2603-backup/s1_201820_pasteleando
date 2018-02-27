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
import javax.inject.Inject;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaLogic 
{ 
    private static final Logger LOGGER = Logger.getLogger(DecoracionPersonalizadaLogic.class.getName());

    @Inject
    private DecoracionPersonalizadaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una decoracionPersonalizada en la persistencia.
     * @param entity La entidad que representa la decoracionPersonalizada a persistir.
     * @return La entiddad de la decoracionPersonalizada luego de persistirla.
     * @throws BusinessLogicException Si la decoracionPersonalizada a persistir ya existe.
     */
    public DecoracionPersonalizadaEntity createDecoracionPersonalizada(DecoracionPersonalizadaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de decoracionPersonalizada");
        // Verifica la regla de negocio que dice que no puede haber dos decoracionPersonalizadaes con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una DecoracionPersonalizada con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la decoracionPersonalizada

        LOGGER.info("Termina proceso de creación de decoracionPersonalizada");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todas las decoracionPersonalizadaes existentes en la base de datos.
     *
     * @return una lista de decoracionPersonalizadaes.
     */
    public List<DecoracionPersonalizadaEntity> getDecoracionesPersonalizadas() {
        LOGGER.info("Inicia proceso de consultar todas las decoracionPersonalizadaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<DecoracionPersonalizadaEntity> decoracionPersonalizadas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las decoracionPersonalizadaes");
        return decoracionPersonalizadas;
    }

    /**
     *
     * Obtener una decoracionPersonalizada por medio de su id.
     *
     * @param id: id de la decoracionPersonalizada para ser buscada.
     * @return la decoracionPersonalizada solicitada por medio de su id.
     */
    public DecoracionPersonalizadaEntity getDecoracionPersonalizada(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar decoracionPersonalizada con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        DecoracionPersonalizadaEntity decoracionPersonalizada = persistence.find(id);
        if (decoracionPersonalizada == null) {
            LOGGER.log(Level.SEVERE, "La decoracionPersonalizada con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar decoracionPersonalizada con id={0}", id);
        return decoracionPersonalizada;
    }

    /**
     *
     * Actualizar una decoracionPersonalizada.
     *
     * @param id: id de la decoracionPersonalizada para buscarla en la base de datos.
     * @param entity: decoracionPersonalizada con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la decoracionPersonalizada con los cambios actualizados en la base de datos.
     */
    public DecoracionPersonalizadaEntity updateDecoracionPersonalizada(Long id, DecoracionPersonalizadaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar decoracionPersonalizada con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        DecoracionPersonalizadaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar decoracionPersonalizada con id={0}", entity.getId());
        return newEntity;
    }
     }
