/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersistence;
import co.edu.uniandes.csw.pasteleando.persistence.PastelPersistence;
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
    private PastelPersistence pastelPersistence;
    
    private DecoracionPersonalizadaLogic personalizadaLo;
    
    private DecoracionCatalogoLogic catalogoLo;
    
    /**
     * Crea una decoracion en la persistencia.
     * @param entity La entidad que representa la decoracion a persistir.
     * @param entityP La entidad que representa la decoracion personalizada a persistir.
     * @param entityC La entidad que representa la decoracion catalogo a persistir.
     * @throws BusinessLogicException Si la decoracion a persistir ya existe.
     */
    public void createDecoracion(DecoracionPersonalizadaEntity entityP, DecoracionCatalogoEntity entityC, DecoracionEntity entity) throws BusinessLogicException {
       
        LOGGER.info("Inicia proceso de creación de decoracion");
        // Una de las reglas de negocio es la siguiente: No puede haber dos decoraciones con el mismo nombre
        if(pastelPersistence.find(entity.getPastel().getId())==null)
        {
            throw new BusinessLogicException("El pastel con el id "+entity.getId());
        }
        if (pastelPersistence.find(entity.getPastel().getId()).getDecoracion() == null) {
            throw new BusinessLogicException("El pastel con id " + entity.getId()+ "ya tiene una decoracion");
        }
         if(entity.getEsPersonalizada()==0)
        {
         personalizadaLo.createDecoracionPersonalizada(entityP);
        }
         else if(entity.getEsPersonalizada()==1)
         {
          catalogoLo.createDecoracionCatalogo(entityC);
         }
        // Invoca la persistencia para crear la decoracion
        
        LOGGER.info("Termina proceso de creación de decoracion");
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
     * @param entity: decoracion con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @param entityP La entidad que representa la decoracion personalizada a persistir.
     * @param entityC La entidad que representa la decoracion catalogo a persistir.
     */
    public void updateDecoracion( DecoracionEntity entity,DecoracionPersonalizadaEntity entityP, DecoracionCatalogoEntity entityC) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar decoracion con id={0}", entity.getId());
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una decoración con id "+ entity);
        }	
           if(pastelPersistence.find(entity.getPastel().getId())==null)
        {
            throw new BusinessLogicException("El pastel con el id "+entity.getId());
        }
        if(entity.getEsPersonalizada()==0)
        {
            personalizadaLo.updateDecoracionPersonalizada(entity.getId(), entityP);
        }
        else if(entity.getEsPersonalizada()==1)
        {
            catalogoLo.updateDecoracionCatalogo(entity.getId(), entityC);
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar decoracion con id={0}", entity.getId());
       
    }

    /**
     * Eliminar una decoración personalizada por ID
     * @param id El ID de la decoración personalizada a eliminar
     */
    public void deleteDecoracion(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar decoración  con id={0}", id);
        if(persistence.find(id)==null)
        {
            throw new BusinessLogicException("No existe una decoración  con ese id");
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar decoración  con id={0}", id);
    }
}
