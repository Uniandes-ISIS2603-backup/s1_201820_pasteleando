package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PromocionPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Promocion.
 * @author jf.garcia
 */
@Stateless
public class PromocionLogic {

    private static final Logger LOGGER = Logger.getLogger(PromocionLogic.class.getName());

    @Inject
    private PromocionPersistence persistence;

    @Inject
    private DecoracionCatalogoLogic decoracionCatalogoLogic;

    /**
     * Obtiene la lista de los registros de Promocion que pertenecen a una decoracion del catalogo.
     *
     * @param decoracionCatalogoId id de la decoración del catálogo el cual es padre de las promociones.
     * @return Colección de objetos de PromocionEntity.
     * @throws co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException Error cuando la decoración del catálogo no tiene promociones.
     */
    public List<PromocionEntity> getPromociones(Long decoracionCatalogoId) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todas las promociones");
        DecoracionCatalogoEntity decoracionCatalogo = decoracionCatalogoLogic.getDecoracionCatalogo(decoracionCatalogoId);
        if (decoracionCatalogo.getPromociones() == null) {
            throw new BusinessLogicException("La decoración del catálogo que consulta aún no tiene promociones");
        }
        if (decoracionCatalogo.getPromociones().isEmpty()) {
            throw new BusinessLogicException("La decoración del catálogo que consulta aún no tiene promociones");
        }
        return decoracionCatalogo.getPromociones();
    }

    /**
     * Obtiene los datos de una instancia de Promocion a partir de su ID.
     * La existencia del elemento padre DecoracionCatalogo se debe garantizar.
     *
     * @param decoracionCatalogoId El id de la decoración del catálogo buscada
     * @param promocionId Identificador de la Promocion a consultar
     * @return Instancia de PromocionEntity con los datos de la Promocion consultado.
     * 
     */
    public PromocionEntity getPromocion(Long decoracionCatalogoId, Long promocionId) {
        return persistence.find(decoracionCatalogoId, promocionId);
    }

    /**
     * Se encarga de crear una promoción en la base de datos.
     *
     * @param entity Objeto de PromocionEntity con los datos nuevos
     * @param decoracionCatalogoId id de la decoración del catálogo el cual sera padre del nuevo Review.
     * @return Objeto de PromocionEntity con los datos nuevos y su ID.
     * 
     */
    public PromocionEntity createPromocion(Long decoracionCatalogoId, PromocionEntity entity) {
        LOGGER.info("Inicia proceso de crear promocion");
        DecoracionCatalogoEntity decoracionCatalogo = decoracionCatalogoLogic.getDecoracionCatalogo(decoracionCatalogoId);
        entity.setDecoracionCatalogo(decoracionCatalogo);
       //TODO: No hay ninguna regla de negocio?  
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Promocion.
     *
     * @param entity Instancia de PromocionEntity con los nuevos datos.
     * @param decoracionCatalogoId id de la decoración del catálogo la cual será padre de la promoción actualizada.
     * @return Instancia de ReviewEntity con los datos actualizados.
     * 
     */
    public PromocionEntity updatePromocion(Long decoracionCatalogoId, PromocionEntity entity) {
        LOGGER.info("Inicia proceso de actualizar promoción");
        DecoracionCatalogoEntity decoracionCatalogo = decoracionCatalogoLogic.getDecoracionCatalogo(decoracionCatalogoId);
        entity.setDecoracionCatalogo(decoracionCatalogo);
        //TODO: No hay ninguna regla de negocio? 
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Promocion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param decoracionCatalogoId id de la decoración del catálogo el cual es padre de la promoción.
     * 
     */
    public void deletePromocion(Long decoracionCatalogoId, Long id) {
        LOGGER.info("Inicia proceso de borrar promocion");
        PromocionEntity old = getPromocion(decoracionCatalogoId, id);
        persistence.delete(old.getId());
    }

}
