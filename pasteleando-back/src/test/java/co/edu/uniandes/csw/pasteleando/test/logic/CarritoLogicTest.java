/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.CarritoLogic;
import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.CarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author MIGUELHOYOS
 */
@RunWith(Arquillian.class)
public class CarritoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CarritoLogic carritoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CarritoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoLogic.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest()
    {
        try {
             utx.begin();
             clearData();
             insertData();
             utx.commit();
        } 
        catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
      
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    public void clearData()
    {
        em.createQuery("delete from CarritoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    public void insertData()
    {
        int i = 0;
        while(i<3)
        {
            CarritoEntity entity = factory.manufacturePojo(CarritoEntity.class);
            data.add(entity);
            em.persist(entity);
            i++;
        }
       
    }
    
    /**
     * Prueba para crear un carrito
     *
     *
     */
    @Test
    public void createCarritoTest() throws BusinessLogicException 
    {
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        newEntity.setCliente(factory.manufacturePojo(ClienteEntity.class));
        newEntity.setPedido(factory.manufacturePojo(PedidoEntity.class));
        CarritoEntity result = carritoLogic.createCarrito(newEntity);
        Assert.assertNotNull(result);
        CarritoEntity entity = em.find(CarritoEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCantidad(), newEntity.getCantidad());
        Assert.assertEquals(entity.getPasteles(), newEntity.getPasteles());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio(),0);
    }
    
    /**
     * Prueba para consultar la lista de Carritos.
     *
     *
     */
    @Test
    public void getCarritosTest()
    {
          List<CarritoEntity> list = carritoLogic.findCarritos();
        Assert.assertEquals(data.size(), list.size());
        for (CarritoEntity entity : list) {
            boolean found = false;
            for (CarritoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un carrito.
     *
     *
     */
    @Test
    public void getCarritoTest() throws BusinessLogicException
    {
        CarritoEntity entity = data.get(0);
        CarritoEntity resultEntity = carritoLogic.findCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCantidad(), resultEntity.getCantidad());
        Assert.assertEquals(entity.getPasteles(), resultEntity.getPasteles());
        Assert.assertEquals(entity.getPrecio(), resultEntity.getPrecio(),0);
    }
    
    /**
     * Prueba para eliminar un carrito
     *
     *
     */
    @Test
    public void deleteCarritoTest() throws BusinessLogicException
    {
        CarritoEntity entity = data.get(0);
        carritoLogic.deleteCarrito(entity.getId());
        CarritoEntity deleted = em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un carrito.
     *
     *
     */
    @Test
    public void updateCarritoTest() throws BusinessLogicException
    {
        CarritoEntity entity = data.get(0);
        CarritoEntity pojoEntity = factory.manufacturePojo(CarritoEntity.class);

        pojoEntity.setId(entity.getId());

        carritoLogic.updateCarrito(pojoEntity);

        CarritoEntity resp = em.find(CarritoEntity.class, entity.getId());
        
        Assert.assertEquals(entity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCantidad(), resp.getCantidad());
        Assert.assertEquals(pojoEntity.getPasteles(), resp.getPasteles());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio(),0);
    }
}
