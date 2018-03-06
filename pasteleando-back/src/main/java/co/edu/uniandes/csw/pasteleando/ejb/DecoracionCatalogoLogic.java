package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
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
     */
    public void deleteDecoracionCatalogo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar decoración del catálogo con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar decoración del catálogo con id={0}", id);
    }
    
    private boolean validateCategoria(String categoria) {
        return !categoria.isEmpty();
    }
    
    /**
     * Obtiene una colección de instancias de PromocionEntity asociadas a una
     * instancia de DecoracionCatalogo
     *
     * @param decoracionCatalogoId Identificador de la instancia de DecoracionCatalogo
     * @return Colección de instancias de PromocionEntity asociadas a la instancia
     * de DecoracionCatalogo
     *
     */
    public List<PromocionEntity> listPromociones(Long decoracionCatalogoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las promociones asociadas a la decoración del catálogo con id = {0}", decoracionCatalogoId);
        return getDecoracionCatalogo(decoracionCatalogoId).getPromociones();
    }
    
    /**
     * Obtiene una instancia de PromocionEntity asociada a una instancia de DecoracionCatalogo
     *
     * @param DecoracionCatalogoId Identificador de la instancia de DecoracionCatalogo
     * @param PromocionId Identificador de la instancia de Promocion
     * @return La entidad de la promoción asociada al libro
     */
    public PromocionEntity getPromocion(Long DecoracionCatalogoId, Long PromocionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una promoción de la decoración del catálogo con id = {0}", DecoracionCatalogoId);
        List<PromocionEntity> list = getDecoracionCatalogo(DecoracionCatalogoId).getPromociones();
        PromocionEntity PromocionEntity = new PromocionEntity();
        PromocionEntity.setId(PromocionId);
        int index = list.indexOf(PromocionEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
    
    /**
     * Asocia un Promocion existente a un DecoracionCatalogo
     *
     * @param DecoracionCatalogoId Identificador de la instancia de DecoracionCatalogo
     * @param PromocionId Identificador de la instancia de Promocion
     * @return Instancia de PromocionEntity que fue asociada a DecoracionCatalogo
     *
     */
    public PromocionEntity addPromocion(Long DecoracionCatalogoId, Long PromocionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar una promoción a la decoración del catálogo con id = {0}", DecoracionCatalogoId);
        DecoracionCatalogoEntity DecoracionCatalogoEntity = getDecoracionCatalogo(DecoracionCatalogoId);
        PromocionEntity PromocionEntity = new PromocionEntity();
        PromocionEntity.setId(PromocionId);
        DecoracionCatalogoEntity.getPromociones().add(PromocionEntity);
        return getPromocion(DecoracionCatalogoId, PromocionId);
    }
    
    /**
     * Remplaza las instancias de Promocion asociadas a una instancia de DecoracionCatalogo
     *
     * @param DecoracionCatalogoId Identificador de la instancia de DecoracionCatalogo
     * @param list Colección de instancias de PromocionEntity a asociar a instancia
     * de DecoracionCatalogo
     * @return Nueva colección de PromocionEntity asociada a la instancia de DecoracionCatalogo
     *
     */
    public List<PromocionEntity> replacePromociones(Long DecoracionCatalogoId, List<PromocionEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar una promocion de la decoración del catálogo con id = {0}", DecoracionCatalogoId);
        DecoracionCatalogoEntity DecoracionCatalogoEntity = getDecoracionCatalogo(DecoracionCatalogoId);
        DecoracionCatalogoEntity.setPromociones(list);
        return DecoracionCatalogoEntity.getPromociones();
    }
    
    /**
     * Desasocia un Promocion existente de un DecoracionCatalogo existente
     *
     * @param DecoracionCatalogoId Identificador de la instancia de DecoracionCatalogo
     * @param PromocionId Identificador de la instancia de Promocion
     *
     */
    public void removePromocion(Long DecoracionCatalogoId, Long PromocionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una promoción de la decoración del catálogo con id = {0}", DecoracionCatalogoId);
        DecoracionCatalogoEntity entity = getDecoracionCatalogo(DecoracionCatalogoId);
        PromocionEntity PromocionEntity = new PromocionEntity();
        PromocionEntity.setId(PromocionId);
        entity.getPromociones().remove(PromocionEntity);
    }
}
