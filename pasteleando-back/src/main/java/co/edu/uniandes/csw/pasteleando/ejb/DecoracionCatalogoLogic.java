package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionCatalogoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de DecoracionCatalogoEntity.
 * @Promocion jf.garcia
 */
@Stateless
public class DecoracionCatalogoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(DecoracionCatalogoLogic.class.getName());
    
    @Inject
    private DecoracionCatalogoPersistence persistence;
    
    /**
     * Devuelve todas las DecoracionCatalogo que hay en la base de datos.
     * @return Lista de entidades de tipo DecoracionCatalogo.
     */
    public List<DecoracionCatalogoEntity> getDecoracionesCatalogo() {
        LOGGER.info("Inicia proceso de consultar todas las decoraciones del catálogo");
        List<DecoracionCatalogoEntity> decoraciones = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las decoraciones del catálogo");
        return decoraciones;
    }
    
    /**
     * Busca una decoración del catálogo por ID
     * @param id El id de la decoración del catálogo a buscar
     * @return La decoración del catálogo encontrado, null si no la encuentra.
     */
    public DecoracionCatalogoEntity getDecoracionCatalogo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar decoración del catálogo con id={0}", id);
        DecoracionCatalogoEntity decoracion = persistence.find(id);
        if (decoracion == null) {
            LOGGER.log(Level.SEVERE, "La decoración del catálogo con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la decoración del catálogo con id={0}", id);
        return decoracion;
    }
    
    /**
     * Guardar una nueva decoración del catálogo
     * @param entity La entidad de tipo decoración del catálogo de la nueva decoración del catálogo a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN ya existe en la persitencia.
     */
    public DecoracionCatalogoEntity createDecoracionCatalogo(DecoracionCatalogoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de decoración del catálogo");
        if (!validateCategoria(entity.getCategoria())) {
            throw new BusinessLogicException("La categoria es inválida");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de la decoración del catálogo");
        return entity;
    }
    
    /**
     * Actualizar una decoración del catálogo por ID
     * @param id El ID de la decoración del catálogo a actualizar
     * @param entity La entidad de la decoración del catálogo con los cambios deseados
     * @return La entidad de la decoración del catálogo luego de actualizarla
     * @throws BusinessLogicException Si la categoría de la actualización es inválida
     */
    public DecoracionCatalogoEntity updateDecoracionCatalogo(Long id, DecoracionCatalogoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar decoración del catálogo con id={0}", id);
        if (!validateCategoria(entity.getCategoria())) {
            throw new BusinessLogicException("La categoría es inválida");
        }
        DecoracionCatalogoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar decoración del catálogo con id={0}", entity.getId());
        return newEntity;
    }
    
    /**
     * Eliminar una decoración del catálogo por ID
     * @param id El ID de la decoración del catálogo a eliminar
     * @throws co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException
     */
    public void deleteDecoracionCatalogo(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar decoración del catálogo con id={0}", id);
        if(persistence.find(id) != null)
        {
            persistence.delete(id);
        }
        else
        {
            throw new BusinessLogicException("No existe una entidad con el id: " +id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de borrar decoración del catálogo con id={0}", id);
    }
    //TODO Validar la categoría es solo ver que no sea vacía?
    private boolean validateCategoria(String categoria) {
        if (persistence.findByCategoria(categoria) != null || categoria.isEmpty()) {
            return false;
        }
        return true;
    }
    
    /**
     * elimina un pastel de una  entidad decoración catalogo
     * @param DecoracionCatalogoId ID de la decoracionCatalogo
     * @param Pastelid ID del pastel que se desea eliminar
     */
    public void removePastel(Long DecoracionCatalogoId, Long Pastelid) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pastel de la decoración del catálogo con id = {0}", DecoracionCatalogoId);
        DecoracionCatalogoEntity entity = getDecoracionCatalogo(DecoracionCatalogoId);
        PastelEntity pastEntity = new PastelEntity(); 
        pastEntity.setId(Pastelid);
        entity.getPasteles().remove(pastEntity); 
    }
}
