/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionCatalogoPersistence;
import co.edu.uniandes.csw.pasteleando.persistence.PastelPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author MIGUELHOYOS
 */
@Stateless
public class PastelLogic {

    @Inject
    private PastelPersistence persistence;
    
    @Inject
    private DecoracionCatalogoLogic decoracionCatalogo;

    /**
     * Se encarga de crear un PASTEL en la base de datos.
     * @param entity Objeto de PastelEntity con los datos nuevos
     * @return Objeto de PastelEntity con los datos nuevos y su ID.
     */
    public PastelEntity createPastel(PastelEntity entity) throws BusinessLogicException {
     
        return persistence.create(entity);
    }

    /**
     * Elimina un PASTEL de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deletePastel(Long id) throws BusinessLogicException {

             persistence.delete(id);
    }

    /**
     * Obtiene la lista de los registros de Pastel.
     * @return Colección de objetos de PastelEntity.
     */
    public List findPasteles() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Pastel a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PastelEntity con los datos del Pastel consultado.
     */
    public PastelEntity findPastel(Long id) throws BusinessLogicException {
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("el pastel con el id:" + id + "no existe");
        }
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Pastel.
     * @param entity Instancia de PastelEntity con los nuevos datos.
     * @return Instancia de PastelEntity con los datos actualizados.
     */
    public PastelEntity updatePastel(PastelEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("el pastel con el id:" + entity.getId() + "no existe");
        }
        return persistence.update(entity);
    }

    public DecoracionCatalogoEntity getDecoracionCatalogo(Long pastelId) throws BusinessLogicException {
        return findPastel(pastelId).getDecoracionCatalogo();
    }

    public void replaceDecoracionCatalogo(Long idPastel, DecoracionCatalogoEntity decoracion) throws BusinessLogicException {
        if (persistence.find(idPastel) == null) {
            throw new BusinessLogicException("el pastel no existe");
        }
        PastelEntity ent = persistence.find(idPastel);
        ent.setDecoracion(decoracion);
        updatePastel(ent);
    }

}
