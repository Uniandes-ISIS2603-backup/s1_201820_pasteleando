/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.CarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author MIGUELHOYOS
 */
public class CarritoLogic {
    @Inject
    private CarritoPersistence persistence;
//TODO: Borrar las variables que no se usan.    
    @Inject
    private PastelLogic pastelLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    public CarritoEntity createCarrito(CarritoEntity entity) throws BusinessLogicException
    {
         //TODO: No se puede validar que existe la entidad con el id porque
        // aun no se tiene el id. EL id es la PK que crea la BD después de persistirlo y hacer commit de la transacción. 
 
        if(persistence.find(entity.getId()) != null)
        {
            throw new BusinessLogicException("el carrito con el id:" + entity.getId() + "ya existe");
        }
        //TODO: No hay ninguna regla de negocio? 
        return persistence.create(entity);
    }
    
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
    
    public List findCarritos()
    {
        return persistence.findAll();
    }
    
    public CarritoEntity findCarrito(Long id) throws BusinessLogicException
    {
        if(persistence.find(id) == null)
        {
            throw new BusinessLogicException("el carrito con el id:" + id + "no existe");
        }
        return persistence.find(id);
    }
    
    public CarritoEntity updateCarrito(CarritoEntity entity)throws BusinessLogicException
    {
        if(persistence.find(entity.getId()) == null)
        {
            throw new BusinessLogicException("el carrito con el id:" + entity.getId()+ "no existe");
        }
       //TODO: No hay ninguna regla de negocio?  
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
        
        //TODO: Esto debe remplazarse por un query en la base de datos. Hacer en la persistencia un método findPastelByCarrito
        List<PastelEntity> listaPasteles = ent.getPasteles();
        boolean enc = false;
        for(int i = 0; i<listaPasteles.size() && !enc; i++)
        {
            if(listaPasteles.get(i).getId() == pastelId)
            {
                listaPasteles.remove(i);
                enc = true;
                ent.setPrecio(ent.getPrecio() - listaPasteles.get(i).getPrecio());
                ent.setCantidad(ent.getCantidad()-1);
            }
        }
        if(!enc)
        {
            throw new BusinessLogicException("el pastel con el id: " + pastelId + "no se encuentra en el carrito con el id: " + id);
        }
        
  }
  //TODO: Porqué este metodo es responsabildiad de esta clase?
  public void replacePedido(Long id, PedidoEntity pedido) throws BusinessLogicException
  {
       CarritoEntity ent = persistence.find(id);
        if(ent == null)
        {
            throw new BusinessLogicException("el carrito con el id: " + id + "no existe");
        }
        ent.setPedido(pedido);
        updateCarrito(ent);
  }
    
}
