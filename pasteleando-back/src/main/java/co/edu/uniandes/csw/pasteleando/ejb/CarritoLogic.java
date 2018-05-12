/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.CarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author MIGUELHOYOS
 */
@Stateless
public class CarritoLogic {
    
    @Inject
    private CarritoPersistence persistence;
    
    public double precioPasteles( CarritoEntity entity)
    {
        List pasteles = entity.getPasteles(); 
        double cantidad = 0; 
        
        for( int i = 0; i < pasteles.size(); i++)
        {
            PastelEntity pastel = (PastelEntity) pasteles.get(i); 
            
            cantidad = cantidad + pastel.getPrecio(); 
        }
        
        return cantidad; 
    }

    /**
     * Se encarga de crear un Carrito en la base de datos.
     * @param entity Objeto de CarritoEntity con los datos nuevos
     * @return Objeto de CarritoEntity con los datos nuevos y su ID.
     */
    public CarritoEntity createCarrito(CarritoEntity entity)throws BusinessLogicException
    {
        if( entity.getPrecio() != precioPasteles(entity))
        {
            throw new BusinessLogicException("El precio del carrito no corresponde");
        }
        return persistence.create(entity);
    }
    
    /**
     * Elimina una instancia de Carrito de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteCarrito(Long id) throws BusinessLogicException
    {
        CarritoEntity ent = persistence.find(id);
        if(ent== null)
        {
            throw new BusinessLogicException("el carrito con el id:" + id + "no existe");
        }
        if(ent.getPedido() != null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no se puede borrar porque tiene un pedido aspciado");
        }
        if(ent.getCliente() != null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no se puede borrar porque tiene un cliente aspciado");
        }
        
        persistence.delete(id);
    }
    
    /**
     * Obtiene la lista de los registros de Carrito.
     * @return Colección de objetos de CarritoEntity.
     */
    public List findCarritos()
    {
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Carrito a partir de su ID.
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CarritoEntity con los datos del Carrito consultado.
     */
    public CarritoEntity findCarrito(Long id) throws BusinessLogicException
    {
        if(persistence.find(id) == null)
        {
            throw new BusinessLogicException("el carrito con el id:" + id + "no existe");
        }
        return persistence.find(id);
    }
    
    /**
     * Actualiza la información de una instancia de Carrito.
     * @param entity Instancia de CarritoEntity con los nuevos datos.
     * @return Instancia de CarritoEntity con los datos actualizados.
     */
    public CarritoEntity updateCarrito(CarritoEntity entity)throws BusinessLogicException
    {
        if(persistence.find(entity.getId()) == null)
        {
            throw new BusinessLogicException("el carrito con el id:" + entity.getId()+ "no existe");
        }

        return persistence.update(entity);
    }
    
    public void replaceCliente(Long id, ClienteEntity cliente) throws BusinessLogicException
    {
        CarritoEntity ent = persistence.find(id);
        if(ent == null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no existe");
        }
        ent.setCliente(cliente);
        updateCarrito(ent);
    }
    
    public List<PastelEntity> getPastelesList(Long id) throws BusinessLogicException
    {
        CarritoEntity ent = persistence.find(id);
        if(ent == null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no existe");
        }
        
        return ent.getPasteles();
    }
    
    public void addPastel(Long id, PastelEntity pastel) throws BusinessLogicException
    {
        CarritoEntity ent = persistence.find(id);
        if(ent == null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no existe");
        }
        List<PastelEntity> listaPasteles = ent.getPasteles();
        if(ent.getPasteles() == null)
        {
            listaPasteles = new ArrayList<>();
        }
        listaPasteles.add(pastel);
        ent.setPasteles(listaPasteles);
        ent.setPrecio(ent.getPrecio() + pastel.getPrecio());
        ent.setCantidad(ent.getCantidad() +1);
        updateCarrito(ent);
    }
    
  public void deletePastel(Long id, Long pastelId) throws BusinessLogicException
  {
      CarritoEntity ent = persistence.find(id);
        if(ent == null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no existe");
        }
        
        PastelEntity pastel = persistence.findPastelByCarrito(pastelId, id);
        
        if(pastel == null)
        {
            throw new BusinessLogicException("el pastel con el id: " + pastelId + "no se encuentra en el carrito con el id: " + id);
        }
        ent.setPrecio(ent.getPrecio() - pastel.getPrecio());
        ent.setCantidad(ent.getCantidad()-1);
        updateCarrito(ent);
  }

}
