/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
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
    //TODO: Borrar loq ue no se use
    //@Inject
    //private DecoracionLogic decoracionLogic;

    public PastelEntity createPastel(PastelEntity entity) throws BusinessLogicException {
        //TODO: No se puede validar que existe la entidad con el id porque
        // aun no se tiene el id. EL id es la PK que crea la BD después de persistirlo y hacer commit de la transacción. 
 
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("el pastel con el id:" + entity.getId() + "ya existe");
        }
        return persistence.create(entity);
    }

    public void deletePastel(Long id) throws BusinessLogicException {

        DecoracionEntity decoracion = getDecoracion(id);
        if (decoracion == null) {
            persistence.delete(id);

        } else {
            persistence.delete(id);
            //decoracionLogic.removePastel(id, decoracion.getId());

        }
    }

    public List findPasteles() {
        return persistence.findAll();
    }

    public PastelEntity findPastel(Long id) throws BusinessLogicException {
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("el pastel con el id:" + id + "no existe");
        }
        
        return persistence.find(id);
    }

    public PastelEntity updatePastel(PastelEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("el pastel con el id:" + entity.getId() + "no existe");
        }
        return persistence.update(entity);
    }

    public DecoracionEntity getDecoracion(Long pastelId) throws BusinessLogicException {
        return findPastel(pastelId).getDecoracion();
    }

    public void replaceDecoracion(Long idPastel, DecoracionEntity decoracion) throws BusinessLogicException {
        if (persistence.find(idPastel) == null) {
            throw new BusinessLogicException("el pastel no existe");
        }
        PastelEntity ent = persistence.find(idPastel);
        ent.setDecoracion(decoracion);
        updatePastel(ent);
    }

}
