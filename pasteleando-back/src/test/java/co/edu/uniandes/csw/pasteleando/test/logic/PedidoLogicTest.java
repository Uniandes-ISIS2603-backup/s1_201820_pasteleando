/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.PedidoLogic;
import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PedidoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author ni.ramirez10
 */

@RunWith(Arquillian.class)
public class PedidoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PedidoLogic pedidoLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PedidoEntity> pedidoData = new ArrayList<>();
    
    private List<PqrsEntity> pqrsData = new ArrayList();
    
     private List<CalificacionEntity> calificacionesData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PedidoEntity.class.getPackage())
                .addPackage(PedidoLogic.class.getPackage())
                .addPackage(PedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    
    private void clearData() 
    {
        em.createQuery("delete from PedidoEntity").executeUpdate();
        em.createQuery("delete from PqrsEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            PqrsEntity pqrs = factory.manufacturePojo(PqrsEntity.class);
            em.persist(pqrs);
            pqrsData.add(pqrs);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            calificacionesData.add(entity);
        }
        
        for (int i = 0; i < 3; i++) 
        {
            PedidoEntity entity = factory.manufacturePojo(PedidoEntity.class);
            em.persist(entity);
            pedidoData.add(entity);
        }
    }
    
    /**
     * Prueba para crear un pedido
     * Throws BusinessLogicException
     */
    
    @Test
    public void createPedidoTest() throws BusinessLogicException 
    {
        try
        {
            PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
            PedidoEntity result = pedidoLogic.createPedido(newEntity); 
            Assert.assertNotNull(result);
            PedidoEntity entity = em.find(PedidoEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getEstado(), entity.getEstado() );
        }
        catch( BusinessLogicException ex )
        {
            Logger.getLogger(PedidoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para consultar la lista de pedidos
     * Throws BusinessLogicException
     */
    
    @Test
    public void getPedidosTest() throws BusinessLogicException 
    {
        List<PedidoEntity> list = pedidoLogic.getPedidos(); 
        //Assert.assertEquals( pedidoData.size(), list.size());
        
        for (PedidoEntity entity : list) 
        {
            boolean found = false;
            
            for (PedidoEntity storedEntity : pedidoData) 
            {
                if ( entity.getId().equals(storedEntity.getId()) ) 
                {
                    found = true;
                }
            }
            
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un pedido
     */
    
    @Test
    public void getPedidoTest() 
    {
        PedidoEntity entity = pedidoData.get(0);
        PedidoEntity resultEntity = pedidoLogic.getPedido( entity.getId() );
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para eliminar un pedido
     * Throws BusinessLogicException
     */
 
    @Test
    public void deletePedidoTest() throws BusinessLogicException 
    {
        PedidoEntity entity = pedidoData.get(0);
        pedidoLogic.deletePedido(entity.getId());
        PedidoEntity deleted = em.find(PedidoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un pedido
     * Throws BusinessLogicException
     */
    
    @Test
    public void updatePedidoTest()  throws BusinessLogicException 
    {
        try
        {
            PedidoEntity entity = pedidoData.get(0);
            PedidoEntity pojoEntity = factory.manufacturePojo(PedidoEntity.class);

            pojoEntity.setId(entity.getId());

            pedidoLogic.updatePedido(pojoEntity.getId(), pojoEntity);

            PedidoEntity resp = em.find(PedidoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado() );
            Assert.assertEquals(pojoEntity.getValueSeRecogePasteleria(), resp.getValueSeRecogePasteleria() );
            
        }
        catch (BusinessLogicException ex) 
        {
            Logger.getLogger(PedidoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
