/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.PastelLogic;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PastelPersistence;
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
public class PastelLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PastelLogic pastelLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private final List<PastelEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PastelEntity.class.getPackage())
                .addPackage(PastelLogic.class.getPackage())
                .addPackage(PastelPersistence.class.getPackage())
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
        em.createQuery("delete from PastelEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    public void insertData()
    {
      
        for(int i = 0; i<3 ; i++)
        {
            PastelEntity entity = factory.manufacturePojo(PastelEntity.class);
            em.persist(entity);
            data.add(entity);

          
        }
       
    }
    
    /**
     * Prueba para crear un pastel
     *
     *
     */
    @Test
    public void createPastelTest() throws BusinessLogicException 
    {
        PastelEntity newEntity = factory.manufacturePojo(PastelEntity.class);
        PastelEntity result = pastelLogic.createPastel(newEntity);
        Assert.assertNotNull(result);
        PastelEntity entity = em.find(PastelEntity.class, result.getId());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getPeso(), newEntity.getPeso());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio(),0);
    }
    
    /**
     * Prueba para consultar la lista de Pasteles.
     *
     *
     */
    @Test
    public void getPastelesTest()
    {
          List<PastelEntity> list = pastelLogic.findPasteles();
        Assert.assertEquals(data.size(), list.size());
        for (PastelEntity entity : list) {
            boolean found = false;
            for (PastelEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una pastel.
     *
     *
     */
    @Test
    public void getPastelTest() throws BusinessLogicException
    {
         PastelEntity entity = data.get(0);
        PastelEntity resultEntity = pastelLogic.findPastel(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPeso(), resultEntity.getPeso());
        Assert.assertEquals(entity.getPrecio(), resultEntity.getPrecio(),0);
    }
    
    /**
     * Prueba para eliminar un pastel.
     *
     *
     */
    @Test
    public void deletePastelTest() throws BusinessLogicException
    {
         PastelEntity entity = data.get(0);
        pastelLogic.deletePastel(entity.getId());
        PastelEntity deleted = em.find(PastelEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un pastel.
     *
     *
     */
    @Test
    public void updatePastelTest() throws BusinessLogicException
    {
        PastelEntity entity = data.get(0);
        PastelEntity pojoEntity = factory.manufacturePojo(PastelEntity.class);

        pojoEntity.setId(entity.getId());

        pastelLogic.updatePastel(pojoEntity);

        PastelEntity resp = em.find(PastelEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPeso(), resp.getPeso());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio(),0);
    }
}
