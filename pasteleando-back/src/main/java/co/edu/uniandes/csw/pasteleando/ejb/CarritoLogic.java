/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.CarritoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author MIGUELHOYOS
 */
public class CarritoLogic {
    @Inject
    private CarritoPersistence persistence;
    
    public CarritoEntity createPastel(CarritoEntity entity) throws BusinessLogicException
    {
        if(!persistence.find(entity.getId()).equals(null))
        {
            throw new BusinessLogicException("el carrito con el id:" + entity.getId() + "ya existe");
        }
        return persistence.create(entity);
    }
    
    public void deletePastel(Long id) throws BusinessLogicException
    {
        if(!persistence.find(id).equals(null))
        {
            throw new BusinessLogicException("el carrito con el id:" + id + "no existe");
        }
        persistence.delete(id);
    }
    
    public List findPasteles()
    {
        return persistence.getAll();
    }
    
    public CarritoEntity findPastel(Long id) throws BusinessLogicException
    {
        if(!persistence.find(id).equals(null))
        {
            throw new BusinessLogicException("el pastel con el id:" + id + "no existe");
        }
        return persistence.find(id);
    }
    
    public CarritoEntity updatePastel(CarritoEntity entity)throws BusinessLogicException
    {
        if(!persistence.find(entity.getId()).equals(null))
        {
            throw new BusinessLogicException("el pastel con el id:" + entity.getId()+ "no existe");
        }
        return persistence.update(entity);
    }
            
}
