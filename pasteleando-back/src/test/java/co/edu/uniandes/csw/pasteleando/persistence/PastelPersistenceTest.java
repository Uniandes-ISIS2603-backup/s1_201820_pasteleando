/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.persistence.PastelPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
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
public class PastelPersistenceTest {
    
    @Deployment
    public JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PastelEntity.class.getPackage())
                .addPackage(PastelPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PastelPersistence pastelPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @javax.inject.Inject
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
    

    private void clearData() {
        em.createQuery("delete from PastelEntity").executeUpdate();
    }
    

    private List<PastelEntity> data = new ArrayList<PastelEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            
            PastelEntity entity = factory.manufacturePojo(PastelEntity.class);

            em.persist(entity);
            
            data.add(entity);
        }
    }


    
    @Test
    public void createPastelTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PastelEntity newEntity = new PastelEntity();
        PastelEntity result = pastelPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        // revisar id
        PastelEntity entity = em.find(PastelEntity.class,result.getPeso());
        Assert.assertEquals(newEntity.getPeso(), entity.getPeso());
    }
    
    @Test
    public void getPastelesTest() {
        List<PastelEntity> list = pastelPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PastelEntity ent : list) {
            boolean found = false;
            for (PastelEntity entity : data) {
                // revisar id
                if (ent.getPeso() == entity.getPeso()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     @Test
    public void getPastelTest() {
        PastelEntity entity = data.get(0);
        //name (metodo creado en la entidad
        PastelEntity newEntity = pastelPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        // id
        Assert.assertEquals(entity.getPeso(), newEntity.getPeso());
    }
    
     @Test
    public void deletePastelTest() {
        PastelEntity entity = data.get(0);
        //id
        pastelPersistence.delete(entity.getId());
        //id
        PastelEntity deleted = em.find(PastelEntity.class, entity.getPeso());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateEditorialTest() {
        PastelEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PastelEntity newEntity = factory.manufacturePojo(PastelEntity.class);

        newEntity.setId(entity.getId());

        pastelPersistence.update(newEntity);

        PastelEntity resp = em.find(PastelEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

}
