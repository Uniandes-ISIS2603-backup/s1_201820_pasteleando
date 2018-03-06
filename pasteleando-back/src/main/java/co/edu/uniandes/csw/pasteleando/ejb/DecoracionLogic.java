/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionLogic {
     private static final Logger LOGGER = Logger.getLogger(DecoracionLogic.class.getName());

    @Inject
    private DecoracionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private PastelLogic pastelLogic;
    /**
     * Crea una decoracion en la persistencia.
     * @param entity La entidad que representa la decoracion a persistir.
     * @return La entiddad de la decoracion luego de persistirla.
     * @throws BusinessLogicException Si la decoracion a persistir ya existe.
     */
    public DecoracionEntity createDecoracion(DecoracionEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de decoracion");
        // Una de las reglas de negocio es la siguiente: No puede haber dos decoraciones con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Decoracion con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la decoracion

        LOGGER.info("Termina proceso de creación de decoracion");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todas las decoraciones existentes en la base de datos.
     *
     * @return una lista de decoraciones.
     */
    public List<DecoracionEntity> getDecoraciones() {
        LOGGER.info("Inicia proceso de consultar todas las decoraciones");
        List<DecoracionEntity> decoracions = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las decoraciones");
        return decoracions;
    }

    /**
     *
     * Obtener una decoracion por medio de su id.
     *
     * @param id: id de la decoracion para ser buscada.
     * @return la decoracion solicitada por medio de su id.
     */
    public DecoracionEntity getDecoracion(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar decoracion con id={0}", id);
        DecoracionEntity decoracion = persistence.find(id);
        if (decoracion == null) {
            LOGGER.log(Level.SEVERE, "La decoracion con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar decoracion con id={0}", id);
        return decoracion;
    }

    /**
     *
     * Actualizar una decoracion.
     *
     * @param id: id de la decoracion para buscarla en la base de datos.
     * @param entity: decoracion con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la decoracion con los cambios actualizados en la base de datos.
     */
    public DecoracionEntity updateDecoracion(Long id, DecoracionEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar decoracion con id={0}", id);
        DecoracionEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar decoracion con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un decoracion
     *
     * @param id: id de la decoracion a borrar
     * @throws BusinessLogicException Si la decoracion a eliminar tiene pasteles.
     */
    public void deleteDecoracion(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar decoracion con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<PastelEntity> pasteles = getPasteles(id);
        if (pasteles == null) {
            persistence.delete(id);

        } else {
            if (pasteles.isEmpty()) {
                persistence.delete(id);
            } else {
                throw new BusinessLogicException("No se puede borrar la decoracion con id " + id + " porque tiene pasteles asociados");
            }

            LOGGER.log(Level.INFO, "Termina proceso de borrar decoracion con id={0}", id);
        }
    }
     /**
     * Agregar un pastel a la decoracion
     *
     * @param pastelId El id pastel a guardar
     * @param decoracionId El id de la decoracion en la cual se va a guardar el
     * pastel.
     * @return El pastel que fue agregado a la decoracion.
     */
    public PastelEntity addPastel(Long pastelId, Long decoracionId) throws BusinessLogicException {
        DecoracionEntity decoracionEntity = getDecoracion(decoracionId);
        PastelEntity pastelEntity = pastelLogic.findPastel(pastelId);
        pastelEntity.setDecoracion(decoracionEntity);
        return pastelEntity;
    }

    /**
     * Borrar un pastel de una decoracion
     *
     * @param pastelId El pastel que se desea borrar de la decoracion.
     * @param decoracionId La decoracion de la cual se desea eliminar.
     */
    public void removePastel(Long pastelId, Long decoracionId) throws BusinessLogicException {
        DecoracionEntity decoracionEntity = getDecoracion(decoracionId);
        PastelEntity pastel = pastelLogic.findPastel(pastelId);
        pastel.setDecoracion(null);
        decoracionEntity.getPasteles().remove(pastel);
    }

    /**
     * Remplazar pasteles de una decoracion
     *
     * @param pasteles Lista de pasteles que serán los de la decoracion.
     * @param decoracionId El id de la decoracion que se quiere actualizar.
     * @return La lista de pasteles actualizada.
     */
    public List<PastelEntity> replacePasteles(Long decoracionId, List<PastelEntity> pasteles) {
        DecoracionEntity decoracion = getDecoracion(decoracionId);
        List<PastelEntity> pastelList = pastelLogic.findPasteles();
        for (PastelEntity pastel : pastelList) {
            if (pasteles.contains(pastel)) {
                pastel.setDecoracion(decoracion);
            } else if (pastel.getDecoracion() != null && pastel.getDecoracion().equals(decoracion)) {
                pastel.setDecoracion(null);
            }
        }
        return pasteles;
    }

    /**
     * Retorna todos los pasteles asociados a una decoracion
     *
     * @param decoracionId El ID de la decoracion buscada
     * @return La lista de pasteles de la decoracion
     */
    public List<PastelEntity> getPasteles(Long decoracionId) {
        return getDecoracion(decoracionId).getPasteles();
    }

    /**
     * Retorna un pastel asociado a una decoracion
     *
     * @param decoracionId El id de la decoracion a buscar.
     * @param pastelId El id del pastel a buscar
     * @return El pastel encontrado dentro de la decoracion.
     * @throws BusinessLogicException Si el pastel no se encuentra en la decoracion
     */
    public PastelEntity getPastel(Long decoracionId, Long pastelId) throws BusinessLogicException {
        List<PastelEntity> pasteles = getDecoracion(decoracionId).getPasteles();
        PastelEntity pastel = pastelLogic.findPastel(pastelId);
        int index = pasteles.indexOf(pastel);
        if (index >= 0) {
            return pasteles.get(index);
        }
        throw new BusinessLogicException("El pastel no está asociado a la decoracion");

    }

    /**
     * Obtiene una colección de instancias de PastelEntity asociadas a una
     * instancia de Decoracion
     *
     * @param decoracionId Identificador de la instancia de Decoracion
     * @return Colección de instancias de PastelEntity asociadas a la instancia de
     * Decoracion
     *
     */
    public List<PastelEntity> listPasteles(Long decoracionId) {
        return getDecoracion(decoracionId).getPasteles();
    }
}
