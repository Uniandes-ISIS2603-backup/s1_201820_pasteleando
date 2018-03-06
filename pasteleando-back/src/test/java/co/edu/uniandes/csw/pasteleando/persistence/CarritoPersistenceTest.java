/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.CarritoEntity;
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
 * @author MIGUELHOYOS
 */
@RunWith(Arquillian.class)
public class CarritoPersistenceTest {

    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private CarritoPersistence carritoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    


    private List<CarritoEntity> data = new ArrayList<>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            
            CarritoEntity entity = factory.manufacturePojo(CarritoEntity.class);

            em.persist(entity);
            
            data.add(entity);
        }
    }

    private void clearData() {
        em.createQuery("delete from CarritoEntity").executeUpdate();
    }

    
    @Test
    public void createCarritoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        CarritoEntity result = carritoPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        CarritoEntity entity = em.find(CarritoEntity.class,result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test
    public void getCarritosTest() {
        List<CarritoEntity> list = carritoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CarritoEntity ent : list) {
            boolean found = false;
            for (CarritoEntity entity : data) {
                if (ent.getId() == entity.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     @Test
    public void getCarritoTest() {
        CarritoEntity entity = data.get(0);
        CarritoEntity newEntity = carritoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        // id
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
     @Test
    public void deleteCarritoTest() {
        CarritoEntity entity = data.get(0);
        carritoPersistence.delete(entity.getId());
        CarritoEntity deleted = em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateCarritoTest() {
        CarritoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);

        newEntity.setId(entity.getId());

        carritoPersistence.update(newEntity);

        CarritoEntity resp = em.find(CarritoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }


}
