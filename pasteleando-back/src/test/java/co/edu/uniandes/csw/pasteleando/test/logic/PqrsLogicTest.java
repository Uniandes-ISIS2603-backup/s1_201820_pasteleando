/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.PqrsLogic;
import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PqrsPersistence;
import java.util.ArrayList;
import java.util.List;
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
public class PqrsLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PqrsLogic pqrsLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PqrsEntity> pqrsData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PqrsEntity.class.getPackage())
                .addPackage(PqrsLogic.class.getPackage())
                .addPackage(PqrsPersistence.class.getPackage())
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
        em.createQuery("delete from PqrsEntity").executeUpdate();
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
    }
    
    /**
     * Prueba para crear una pqrs
     * Throws BusinessLogicException
     */
    
    @Test
    public void createPqrsTest() throws BusinessLogicException 
    {
        PqrsEntity newEntity = factory.manufacturePojo(PqrsEntity.class);
        PqrsEntity result = pqrsLogic.createPqrs(newEntity); 
        Assert.assertNotNull(result);
        PqrsEntity entity = em.find(PqrsEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de pqrs
     * Throws BusinessLogicException
     */
    
    @Test
    public void getLPqrsTest() throws BusinessLogicException 
    {
        List<PqrsEntity> list = pqrsLogic.getPqrs(); 
        //Assert.assertEquals( pqrsData.size(), list.size());
        
        for (PqrsEntity entity : list) 
        {
            boolean found = false;
            
            for (PqrsEntity storedEntity : pqrsData) 
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
     * Prueba para consultar una pqrs
     */
    
    @Test
    public void getPqrsTest() 
    {
        PqrsEntity entity = pqrsData.get(0);
        PqrsEntity resultEntity = pqrsLogic.getPqrs(entity.getId() );
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para eliminar una pqrs
     * Throws BusinessLogicException
     */
 
    @Test
    public void deletePqrsTest() throws BusinessLogicException 
    {
        PqrsEntity entity = pqrsData.get(0);
        pqrsLogic.deletPqrs(entity.getId());
        PqrsEntity deleted = em.find(PqrsEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una pqrs
     * Throws BusinessLogicException
     */
    
    @Test
    public void updatePqrsTest()  throws BusinessLogicException 
    {
        PqrsEntity entity = pqrsData.get(0);
        PqrsEntity pojoEntity = factory.manufacturePojo(PqrsEntity.class);

        pojoEntity.setId(entity.getId());

        pqrsLogic.updatePqrs(pojoEntity.getId(), pojoEntity);

        PqrsEntity resp = em.find(PqrsEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
    
    
}
